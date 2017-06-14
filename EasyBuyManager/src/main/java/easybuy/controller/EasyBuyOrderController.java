package easybuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import easybuy.pojo.EasyBuyOrder;
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
     * 进入我的订单页面
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> order(HttpServletRequest request){
    	Map<String, Object> results = new HashMap<String, Object>();
        String userIdParam=request.getParameter("userId");
    	// 设置订单Id
    	Long eoId =null;
    	// 设置用户Id
    	Long userId =null;
    	if(userIdParam!=null&&!"".equals(userIdParam.trim())){
    		userId =Long.parseLong(userIdParam.trim());
    	}
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
    	String userName = request.getParameter("userName");
    	// 设置每页显示数量
    	int pageSize = 8;
    	// 获取记录总数
    	long maxRowsCount = easyBuyOrderService.count(status,eoId, userId,userName);
    	// 分页工具
    	PageUtil pageUtil =new PageUtil(pageSize, maxRowsCount);
    	// 获取订单集合
        List<EasyBuyOrderVo> ordervos =	easyBuyOrderService.listVo(status,eoId, userId, userName, targetPage, pageSize);
        results.put("ordervos", ordervos);
    	results.put("targetPage", targetPage);
    	results.put("pageSize", pageSize);
    	results.put("maxCount", maxRowsCount);
    	results.put("maxPage",pageUtil.getMaxPage());
        return results;
    }
    
    /**
     * 修改订单状态
     * @param request
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public int  updateStatus(HttpServletRequest request){
    // 获取现有状态
    String status =	request.getParameter("status");
    // 状态逻辑处理
    if(status.equals("1")){
    	status="已取消";
    } else if (status.equals("2")){
    	status="已发货";
    }
    else if (status.equals("3")){
    	status="未发货";
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
    if(easyBuyOrderService.updateStatus(easyBuyOrder)>0){
    	return 1;
    }
    return 0;
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public int delete(HttpServletRequest request){
    	String eoId = request.getParameter("eoId");
    	if(easyBuyOrderService.delete(Long.parseLong(eoId))>0){
    		return 1;
    	}
    	return 0;
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
