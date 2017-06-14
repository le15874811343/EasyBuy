package easybuy.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import easybuy.pojo.EasyBuyOrder;
import easybuy.pojo.EasyBuyProduct;
import easybuy.pojo.EasyBuyUser;
import easybuy.pojo.vo.EasyBuyOrderVo;
import easybuy.service.IEasyBuyOrderService;
import easybuy.service.IEasyBuyProductService;
import easybuy.util.PageUtil;

/**
 * 订单处理controller
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/order")
public class EasyBuyOrderController {
	@Resource
  private IEasyBuyOrderService easyBuyOrderService;
 
	@Resource
  private IEasyBuyProductService easyBuyProductService;
	/**
	 * 提交支付
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/submit.html")
	public String submitOrder(HttpServletRequest request,HttpServletResponse response){
    	// 产品Id数组
        String eids = request.getParameter("epids");
        // 数量分组
        String numbers = request.getParameter("numbers");
        // 分割
        String [] eid = eids.split(";");
        // 分割
        String [] number = numbers.split(";");
        // 创建订单对象
    	EasyBuyOrder easyBuyOrder = new EasyBuyOrder();
    	// 获取登录用户
    	EasyBuyUser easyBuyUser = (EasyBuyUser) request.getSession().getAttribute("user");
    	// 设置订单I的，总数加1
    	easyBuyOrder.setEoId(easyBuyOrderService.countAll()+1);
    	// 设置初始状态
       	easyBuyOrder.setEoStatus("未发货");
       	// 设置订单用户ID
    	easyBuyOrder.setEoUserId(Long.parseLong(easyBuyUser.getEuUserId()));
    	// 时间格式化
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	// 设置创建时间
    	easyBuyOrder.setEoCreateTime(sdf.format(new Date()));
    	// 遍历各个商品
    	for(int i=0;i<eid.length;i++){
    	// 设置商品ID
    	easyBuyOrder.setEoProId(eid[i]);
    	// 设置商品数量
    	easyBuyOrder.setNumber(Long.parseLong(number[i]));
    	// 获取商品详细信息,用于获取库存
    	EasyBuyProduct easyBuyProduct = easyBuyProductService.getById(Long.parseLong(eid[i]));
    	// 创建修改用的商品对象
    	EasyBuyProduct easyBuyProducts = new EasyBuyProduct();
    	// 设置修改对象的Id
    	easyBuyProducts.setEpId(easyBuyProduct.getEpId());
    	// 设置库存，减去被购买数量
    	easyBuyProducts.setEpStock(easyBuyProduct.getEpStock()-Long.parseLong(number[i]));
    	// 格式工具
    	DecimalFormat df = new DecimalFormat("#.00");
    	// 设置金额
    	easyBuyOrder.setEoCost(Double.parseDouble(df.format((easyBuyOrder.getNumber()*easyBuyProduct.getEpPrice()*easyBuyProduct.getEpRebate()))));
    	// 修改商品对象
    	easyBuyProductService.upDate(easyBuyProducts);
    	// 创建订单
    	easyBuyOrderService.saveOrder(easyBuyOrder);
    	// 创建cookie 用于清空购物车
    	Cookie cookie = new Cookie(easyBuyUser.getEuUserName(), null);
    	// 设置cookie存在时间 3个月
    	cookie.setMaxAge(60*60*24*30*3);
    	// 设置cookie能被所有路径请求
	    cookie.setPath("/");
	    // 添加到cookie中
	    response.addCookie(cookie);
    	}
		return "login/shopping-result";
	}
    
    /**
     * 进入我的订单页面
     * @param request
     * @return
     */
    @RequestMapping("/view.html")
    public String order(HttpServletRequest request){
    	// 获取登录用户
    	EasyBuyUser user = (EasyBuyUser) request.getSession().getAttribute("user");
    	// 设置订单Id
    	Long eoId =null;
    	// 设置用户Id
    	Long userId =Long.parseLong(user.getEuUserId());
    	// 设置订单状态
    	String status =null;
    	// 设置目标页
    	int targetPage =1;
    	// 获取页面传递的目标页
    	String page = request.getParameter("targetPage");
    	// 页码逻辑处理
    	if(null!=page&&!"".equals(page)){
    		targetPage = Integer.parseInt(page);
    	}
    	// 获取页面传递的订单
    	String eoI = request.getParameter("eoId");
    	// 订单Id逻辑处理
    	if(null!=eoI&&!"".equals(eoI)){
    		eoId = Long.parseLong(eoI);
    	}
    	// 获取页面传递的状态
    	String statu = request.getParameter("status");
    	// 状态逻辑处理
    	if(null!=statu&&!"".equals(statu)){
    		status = statu;
    	}
    	// 设置每页显示数量
    	int pageSize = 6;
    	// 获取记录总数
    	long maxRowsCount = easyBuyOrderService.count(status,eoId, userId);
    	// 分页工具
    	PageUtil pageUtil =new PageUtil(pageSize, maxRowsCount);
    	// 获取订单集合
        List<EasyBuyOrderVo> ordervos =	easyBuyOrderService.listVo(status,eoId, userId, targetPage, pageSize);
        // 存入订单集合
        request.setAttribute("ordervos", ordervos);
        // 存入当前页
        request.setAttribute("targetPage", targetPage);
        // 存入最大页码
	    request.setAttribute("maxPage", pageUtil.getMaxPage());
	    // 存入记录总数
	    request.setAttribute("maxCount", maxRowsCount);
	    // 存入状态
	    request.setAttribute("status", status);
	    // 存入订单Id
	    request.setAttribute("eoId", eoId);
        return "login/order";
    }
    
    /**
     * 修改订单状态
     * @param request
     * @return
     */
    @RequestMapping("/updateStatus.html")
    public String  updateStatus(HttpServletRequest request){
    // 获取现有状态
    String status =	request.getParameter("status");
    // 状态逻辑处理
    if(status.equals("1")){
    	status="取消审核中";
    } else if (status.equals("2")){
    	status="已收货";
    }
    // 获取订单Id
    String eoId = request.getParameter("eoId");
    // 创建订单对象
    EasyBuyOrder easyBuyOrder = new EasyBuyOrder();
    // 设置订单Id
    easyBuyOrder.setEoId(Long.parseLong(eoId));
    // 设置订单状态
    easyBuyOrder.setEoStatus(status);
    // 修改状态
    easyBuyOrderService.updateStatus(easyBuyOrder);
    return "redirect:view.html";
    }
	public IEasyBuyOrderService getEasyBuyOrderService() {
		return easyBuyOrderService;
	}

	public void setEasyBuyOrderService(IEasyBuyOrderService easyBuyOrderService) {
		this.easyBuyOrderService = easyBuyOrderService;
	}
	public IEasyBuyProductService getEasyBuyProductService() {
		return easyBuyProductService;
	}
	public void setEasyBuyProductService(
			IEasyBuyProductService easyBuyProductService) {
		this.easyBuyProductService = easyBuyProductService;
	}
	
	
}
