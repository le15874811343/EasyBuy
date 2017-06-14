package easybuy.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import easybuy.pojo.EasyBuyUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import easybuy.pojo.EasyBuyProduct;
import easybuy.service.IEasyBuyProductService;

/**
 * 购物车控制器
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingController {
   @Resource
   private IEasyBuyProductService easyBuyProductService;
   
   /**
    * 进入购物车
    * @param request
    * @return
    * @throws Exception
    */
	@RequestMapping(value="/view.html")
	public String goShoppingView(HttpServletRequest request) throws Exception{
		// 购物车商品json字符串
		String carProductJosn = null;
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		// 获取当前登录用户
		EasyBuyUser user = (EasyBuyUser) request.getSession().getAttribute("user");
		// 循环cookie，找到存当前用户购物车信息的cookie
		for(Cookie cookie : cookies){
			// 如果用户存在购物车信息
			if(cookie.getName().equals(user.getEuUserName())){
			  // 获取cookie中的值
			  carProductJosn = cookie.getValue();
			  // UTF-8解码
			  carProductJosn = URLDecoder.decode(carProductJosn, "UTF-8");
			}
		}
		// 非空判断是否存在购物车信息,没有则直接返回页面
		if(carProductJosn == null || "".equals(carProductJosn.trim())){
			return "login/shopping";
		}
		// json转map
	    Map<String, EasyBuyProduct> carProduct = JSONObject.fromObject(carProductJosn);
	    // 存入购物车信息
	    request.setAttribute("carProduct", carProduct);
		return "login/shopping";
	}
	
	/**
	 * 添加商品到购物车
	 * @param request
	 * @param url
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCar.html")
	public String addCar(HttpServletRequest request,String url,HttpServletResponse response) throws Exception{
		// 商品id
		String epId = request.getParameter("epId");
		// 创建购物车商品map
		Map<String, EasyBuyProduct> carProduct = new HashMap<String, EasyBuyProduct>();
		// 商品Id非空判断
		if(epId == null || "".equals(epId.trim())){
			return "redirect:"+url;
		}
		// 根据商品Id获取商品详情
		EasyBuyProduct easyBuyProduct = easyBuyProductService.getById(Long.parseLong(epId));
		// 商品非空判断
		if(easyBuyProduct==null){
			return "redirect:"+url;
		}
		// 商品名称中文转码
		easyBuyProduct.setEpName(URLEncoder.encode(easyBuyProduct.getEpName(),"UTF-8"));
		// 创建购物车商品json字符串
		String carProductJosn = null;
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		// 获取当前用户
		EasyBuyUser user = (EasyBuyUser) request.getSession().getAttribute("user");
		// 获取当前用户以前的购物车信息
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(user.getEuUserName())){
			  carProductJosn = cookie.getValue();
			}
		}
		// 设置商品初始数量为1
		easyBuyProduct.setUserCout(1);
		// 存入购物车Map中
		carProduct.put(easyBuyProduct.getEpId().toString(), easyBuyProduct);
		// 如果用户之前购物车中有商品
		if(carProductJosn != null && !"".equals(carProductJosn.trim())){
			// 购物车map指向之前的商品信息
			carProduct = JSONObject.fromObject(carProductJosn);
			// 判断购物车中是否存在本次要添加的商品
			if( carProduct.containsKey(easyBuyProduct.getEpId().toString())){
				// 如果存在，获取之前该商品的记录
				String json =  JSONObject.fromObject(carProductJosn).get(easyBuyProduct.getEpId().toString()).toString();
				// 设置该商品的数量在之前的基础上加1
				easyBuyProduct.setUserCout(Integer.parseInt(json.substring(json.lastIndexOf(":")+1, json.length()-1))+1);
			}
			// 将该商品存入购物车中
		    carProduct.put(easyBuyProduct.getEpId().toString(), easyBuyProduct);
		}
		// map转json
		carProductJosn =  JSONObject.fromObject(carProduct).toString();
		// 创建新的购物车cookie
	    Cookie  cookie = new Cookie(user.getEuUserName(),carProductJosn);
	    // 设置cookie存在时间3个月
	    cookie.setMaxAge(60*60*24*30*3);
	    // 设置cookie可被所有请求路径请求
	    cookie.setPath("/");
	    // 添加cookie
	    response.addCookie(cookie);
		return "redirect:/shopping/view.html";
	}
	
	/**
	 * 立即购买
	 * @param request
	 * @param url
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/quik.html")
	public String quikBuy(HttpServletRequest request,String url,HttpServletResponse response) throws Exception{
		// 商品Id
		String epId = request.getParameter("epId");
		// 非空判断
		if(epId == null || "".equals(epId.trim())){
			return "redirect:"+url;
		}
		// 根据商品Id获取商品详情
		EasyBuyProduct easyBuyProduct = easyBuyProductService.getById(Long.parseLong(epId));
		// 商品非空判断
		if(easyBuyProduct==null){
			return "redirect:"+url;
		}
		// 设置初始购买数量为1
        easyBuyProduct.setUserCout(1);
        request.setAttribute("quikPro", easyBuyProduct);
		return "login/quikshopping";
	}
	
	/**
	 * 从购物车中删除某商品
	 * @param request
	 * @param url
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete.html")
	public String deleteCar(HttpServletRequest request,String url,HttpServletResponse response){
		// 获取商品Id
		String epId = request.getParameter("epId");
		// 创建购物车Map
		Map<String, EasyBuyProduct> carProduct = new HashMap<String, EasyBuyProduct>();
		// 商品Id非空判断
		if(epId == null || "".equals(epId.trim())){
			return "redirect:"+url;
		}
		// 购物车商品Json字符串
		String carProductJosn = null;
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		// 获取当前登录用户
		EasyBuyUser user = (EasyBuyUser) request.getSession().getAttribute("user");
		// 获取当前用户购物车信息cookie
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(user.getEuUserName())){
			  carProductJosn = cookie.getValue();
			}
		}
		// 购物车信息非空判断
		if(carProductJosn != null && !"".equals(carProductJosn.trim())){
			// 购物车指向购物车cookie
			carProduct = JSONObject.fromObject(carProductJosn);
			// 移除该商品
		    carProduct.remove(epId);
		}
		carProductJosn =  JSONObject.fromObject(carProduct).toString();
	    Cookie  cookie = new Cookie(user.getEuUserName(),carProductJosn);
	    cookie.setMaxAge(60*60*24*30*3);
	    cookie.setPath("/");
	    response.addCookie(cookie);
		return "redirect:/shopping/view.html";
	}
	
	/**
	 * 更改购物车商品数量
	 * @param request
	 * @param url
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/update.html")
	public String updateCar(HttpServletRequest request,String url,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		// 购物车集合
		Map<String, EasyBuyProduct> carProduct = new HashMap<String, EasyBuyProduct>();
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		// 用户购物车商品json字符串
		String carProductJosn = null;
		// 商品Id
		String epId = request.getParameter("epId");
		// 商品数量
		String number = request.getParameter("number");
		// 商品Id非空判断
		if(epId == null || "".equals(epId.trim())){
			return "redirect:"+url;
		}
		// 根据商品Id获取商品信息
		EasyBuyProduct easyBuyProduct = easyBuyProductService.getById(Long.parseLong(epId));
		// 商品非空判断
		if(easyBuyProduct==null){
			return "redirect:"+url;
		}
		// 设置商品名称转码
		easyBuyProduct.setEpName(URLEncoder.encode(easyBuyProduct.getEpName(),"UTF-8"));
		// 获取当前登录的用户
		EasyBuyUser user = (EasyBuyUser) request.getSession().getAttribute("user");
		// 获取购物车cookie
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(user.getEuUserName())){
			  carProductJosn = cookie.getValue();
			}
		}
		// 设置商品购买数量
		easyBuyProduct.setUserCout(Integer.parseInt(number));
		// json转Map
		carProduct = JSONObject.fromObject(carProductJosn);
		// 覆盖之前的该商品
	    carProduct.put(easyBuyProduct.getEpId().toString(), easyBuyProduct);
	    // map转json
	    carProductJosn =  JSONObject.fromObject(carProduct).toString();
	    // 创建购物车cookie，覆盖之前的cookie
	    Cookie  cookie = new Cookie(user.getEuUserName(),carProductJosn);
	    // 设置cookie存在时间3个月
	    cookie.setMaxAge(60*60*24*30*3);
	    // 设置cookie能被所有请求请求
	    cookie.setPath("/");
	    // 添加cookie
	    response.addCookie(cookie);
	    // 添加成功
		response.getWriter().print("1");
		response.getWriter().flush();//清空缓存,刷新
		response.getWriter().close();
		return null;
	}
	
	/**
	 * 购买检测
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/chek.html")
	public void chek(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		// 获取库存不足的商品名称和现有库存
		String epName = havaCount(request, response);
		// 获取当前登录用户
		EasyBuyUser easyBuyUser = (EasyBuyUser) request.getSession().getAttribute("user");
		// 判断用户手机是否填写
		if(easyBuyUser.getEuMobile()!=null && !easyBuyUser.getEuMobile().equals("")){
		// 判断用户收货地址是否填写
			if(easyBuyUser.getEuAddress()!=null && !easyBuyUser.getEuAddress().equals("")){
				// 判断是否有库存不足的商品
				if(epName==null){
					// 通过
					response.getWriter().print("1");
				}else {
					    // 库存不足
						response.getWriter().print(epName);
					}
			} else {
				// 收货地址未填写
				response.getWriter().print("2");
			}
		} else {
			 // 手机号未填写
			 response.getWriter().print("0");
			}
		 response.getWriter().flush();//清空缓存,刷新
		 response.getWriter().close();
	}
	
	/**
	 * 是否有库存检测
	 * @param request
	 * @param response
	 * @return
	 */
	private String havaCount(HttpServletRequest request,HttpServletResponse response){
		// 商品id字符串集
		String epIds = request.getParameter("eids");
		// 商品购买数量字符串集
		String numbers = request.getParameter("numbers");
		// 分割商品Id
		String[] epid = epIds.split(";");
		// 分割商品购买数量
		String[] number = numbers.split(";");
		// 商品对象
		EasyBuyProduct buyProduct =null;
		// 遍历商品Id
		for(int i=0;i<epid.length;i++){
		 // 获取商品详情
		 buyProduct =	easyBuyProductService.getById(Long.parseLong(epid[i]));
		 // 商品购买数量和库存比较
		 if(buyProduct.getEpStock()-Long.parseLong(number[i])<0){
			 //  返回商品名称和现有库存
			 return buyProduct.getEpName()+";"+buyProduct.getEpStock();
		 }
		}
		 return null;	
		 }
	public IEasyBuyProductService getEasyBuyProductService() {
		return easyBuyProductService;
	}
	public void setEasyBuyProductService(
			IEasyBuyProductService easyBuyProductService) {
		this.easyBuyProductService = easyBuyProductService;
	}
	
	
}
