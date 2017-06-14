package easybuy.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import easybuy.pojo.EasyBuyNews;
import easybuy.pojo.EasyBuyProduct;
import easybuy.pojo.EasyBuyUser;
import easybuy.service.IEasyBuyNewsService;
import easybuy.service.IEasyBuyProductService;
import easybuy.service.IProductCategoryService;

/**
 * 主页面控制器
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/main")
public class MainController {
	
	@Resource
	private IEasyBuyProductService productService;
	
	@Resource
	private IProductCategoryService productCategoryService;
	
	@Resource
	private IEasyBuyNewsService easyBuyNewsService;
	
	@RequestMapping(value="/welcome.html")
    public String goIndex(HttpServletRequest request) throws Exception{
		// 特价商品
    	Map<Long, EasyBuyProduct> specialOffers = specialOffer();
    	// 热卖推荐
    	Map<Long, EasyBuyProduct> bestSells = bestSell();
    	// 公告
    	Map<Long, EasyBuyNews> actives = actives();
    	// 新闻
    	Map<Long, EasyBuyNews> news = news();
    	// 最近添加购物车处理
		String carProductJosn = null;
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		// 获取当前登录用户
		EasyBuyUser user = (EasyBuyUser) request.getSession().getAttribute("user");
		// 判断是否登录
		if(user!=null){
		// 获取购物车信息cookie
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(user.getEuUserName())){
			  carProductJosn = cookie.getValue();
			  // 商品用户名解码
			  carProductJosn = URLDecoder.decode(carProductJosn, "UTF-8");
			}
		}
		// 判断是否存在购物车信息
		if(carProductJosn != null && !"".equals(carProductJosn.trim())){
			// 分割json字符串，去最后，最新的三条商品
		    String[] json =	carProductJosn.split("},");
		    // 大于两个商品才需要处理
		    if(json.length>1){
		    	// 重置购物车信息json字符串
		    	carProductJosn = "";
		    // 拼接字符串，从末尾往前取，取三个商品
		    for(int i=json.length-1;i>=0&&i>json.length-5;i--){
		    	// 临时结果对象
		    	String result = "";
		    	// 尾部处理
		    	if(json[i].lastIndexOf("}}")>=0){
		    		result = json[i].substring(0, json[i].lastIndexOf("}}"))+"},";
		    	}else if(json[i].indexOf("{")==0){//头部处理
		    		result = json[i].substring(1,json[i].length())+"}";
		    	}
		    	else {
		    		result = json[i]+"},"; // 中间部分
		    	}
		    	carProductJosn+= result; //拼接
		    	}
		     carProductJosn = "{"+carProductJosn+"}"; //拼接
		    }
		    // json转map
			Map<String, EasyBuyProduct> carProduct = JSONObject.fromObject(carProductJosn);
		    request.getSession().setAttribute("already", carProduct);
		}
	}
    	request.setAttribute("specialOffers", specialOffers);
    	request.setAttribute("bestSells", bestSells);
    	request.setAttribute("news", news);
    	request.setAttribute("actives", actives);
    	return "index";
    }
	/**
	 * 特价商品
	 * @return
	 */
	private Map<Long, EasyBuyProduct> specialOffer(){
		// 获取打折幅度最高的前8条数据
		return productService.listByRebate(9);
	}
    
	/**
	 * 热卖推荐
	 * @return
	 */
	private Map<Long, EasyBuyProduct> bestSell(){
		// 获取销量最高的前12条数据
		return productService.listBySaleCount(13);
	}

	/**
	 * 新闻
	 * @return
	 */
	private Map<Long, EasyBuyNews> news(){
		// 获取最新的前7条新闻
		return easyBuyNewsService.listByDate(8, 1);
	}
	
	/**
	 * 公告
	 * @return
	 */
	private Map<Long, EasyBuyNews> actives(){
		// 获取最新的前7条动态
	   return easyBuyNewsService.listByDate(8, 0);
	}
	
	public IEasyBuyProductService getProductService() {
		return productService;
	}


	public void setProductService(IEasyBuyProductService productService) {
		this.productService = productService;
	}
	public IProductCategoryService getProductCategoryService() {
		return productCategoryService;
	}
	public void setProductCategoryService(
			IProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	public IEasyBuyNewsService getEasyBuyNewsService() {
		return easyBuyNewsService;
	}
	public void setEasyBuyNewsService(IEasyBuyNewsService easyBuyNewsService) {
		this.easyBuyNewsService = easyBuyNewsService;
	}
	
	
	
	
}
