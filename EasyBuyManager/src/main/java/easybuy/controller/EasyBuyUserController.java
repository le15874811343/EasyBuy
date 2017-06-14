package easybuy.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.pojo.EasyBuyUser;
import easybuy.service.IEasyBuyUserService;
import easybuy.util.PageUtil;
/**
 * 用户处理controller
 * @author 乐家良
 *
 */
@Controller
@RequestMapping("/user")
public class EasyBuyUserController {

	@Resource
	private IEasyBuyUserService easyBuyUserService;
	
	/**
	 * 登录
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public EasyBuyUser login(HttpServletRequest request,String url){
		// 用户名
		String userName = request.getParameter("userName");
		// 密码
		String password = request.getParameter("password");
		// 非空判断
		if(null == userName  || "".equals(userName.trim())||null == password || "".equals(password.trim())){
			request.setAttribute("error", "录入信息有误,请重新输入");
			return null;
		}
		// 创建用户
		EasyBuyUser loginUser = new EasyBuyUser();
		// 设置用户名
		loginUser.setEuUserName(userName);
		// 设置密码
		loginUser.setEuPassword(password);
		// 登录
		loginUser = easyBuyUserService.login(loginUser);
		// 非空判断
		if(loginUser != null && loginUser.getEuStatus()!=null && loginUser.getEuStatus()==1){
			request.getSession().setAttribute("user", loginUser);
			return loginUser;
		} else {
			request.setAttribute("notMatch", "用户名或密码输入错误,请重新输入");
			return null;
		}
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/reg")
	@ResponseBody
	public String reg(HttpServletRequest request){
		// 用户名
		String userName = request.getParameter("userName");
		// 密码
		String password = request.getParameter("password");
		// 非空判断
		if(null == userName  || "".equals(userName.trim())||null == password || "".equals(password.trim())){
			return "2";
		}
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String addr = request.getParameter("addr");
		String admin = request.getParameter("admin");
		// 创建用户
		EasyBuyUser saveUser = new EasyBuyUser();
		// 设置用户名
		saveUser.setEuUserName(userName);
		// 设置密码
		saveUser.setEuPassword(password);
		saveUser.setEuSex(sex);
		saveUser.setEuMobile(mobile);
		saveUser.setEuAddress(addr);
		saveUser.setEuStatus(Integer.parseInt(admin));
		// 保存
		saveUser = easyBuyUserService.saveUser(saveUser);
		if(saveUser != null){
			return "1";
		} else {
			return "0";
		}
	}
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping("/out.html")
	public String loginOut(HttpServletRequest request){
		// 销毁登录用户
		request.getSession().removeAttribute("user");
		return "redirect:/main/welcome.html";
	}
	
	/**
	 * 登录匹配比较
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/match.html")
	public void IsMatch(HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		// 验证码检测
		if(codeIsMatch(request)){
			// 用户名重复检测
			if(userIsExist(request)){
				// 账号密码检测
				if(isPass(request)!=null){
				 if(isAdmin(request, isPass(request))){
				 // 通过
				 response.getWriter().print("1");
				} else {
				  response.getWriter().print("4");
				}
				} else {
					// 用户名密码错误
					response.getWriter().print("3");
				}
			} else{
				// 用户不存在
				response.getWriter().print("2");
			} 
		}
		else {
			 // 验证码错误
			 response.getWriter().print("0");
		}
		response.getWriter().flush();//清空缓存,刷新
		response.getWriter().close();
	}
	
	@RequestMapping("/loginUser")
	@ResponseBody
	public  EasyBuyUser getLoginUser(HttpServletRequest request){
		if(request.getSession().getAttribute("user")==null){
			return null;
		}
		return (EasyBuyUser) request.getSession().getAttribute("user");
	}
	/**
	 * 注册匹配比较
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/regmatch")
	public void regIsMatch(HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setContentType("text/html;charset=utf-8");
			// 用户名重复验证
			if(userIsExist(request)){
				// 用户名已存在
				response.getWriter().print("2");
			}else{
				// 通过
				response.getWriter().print("1");
			}
			response.getWriter().flush();//清空缓存,刷新
			response.getWriter().close();
	}
	
	@RequestMapping("/modifyView")
	@ResponseBody
	public EasyBuyUser modifyView(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		if(null == userId || "".equals(userId.trim())){
			return null;
		}
	    return	easyBuyUserService.getById(userId);
	}
	
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/modify.html")
	public void Modify(HttpServletRequest request,HttpServletResponse response) throws Exception{
	response.setContentType("text/html;charset=utf-8");
	// 手机
	String mobile =	request.getParameter("mobile");
	// 密码
	String passWord = request.getParameter("passWord");
	// 性别
	String sex  =	request.getParameter("sex");
	// 收货地址
	String address =	request.getParameter("address");
	String userName = request.getParameter("userName");
	String userId = request.getParameter("userId");
	// 创建用户
	EasyBuyUser easyBuyUser = new EasyBuyUser();
	
	// 设置用户名
	easyBuyUser.setEuUserName(userName);
	// 设置用户Id
	easyBuyUser.setEuUserId(userId);
	// 设置手机
	easyBuyUser.setEuMobile(mobile);
	// 设置密码
	easyBuyUser.setEuPassword(passWord);
	// 设置性别
	easyBuyUser.setEuSex(sex);
	// 设置收货地址
	easyBuyUser.setEuAddress(address);
	// 修改用户
	easyBuyUser = easyBuyUserService.updateUser(easyBuyUser);
	// 检测是否修改成功
	if(easyBuyUser!=null){
		// 自动登入修改后的用户
		request.getSession().setAttribute("user",easyBuyUser );
		response.getWriter().print("1");
	}
	response.getWriter().flush();//清空缓存,刷新
	response.getWriter().close();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(HttpServletRequest request){
		String userId = request.getParameter("userId");
		if(userId==null || "".equals(userId.trim())){
			return 0;
		}
		return easyBuyUserService.delete(userId);
	}
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request){
    	// 获取页面传递的目标页
    	String page = request.getParameter("targetPage");
    	// 设置目标页
    	int targetPage =1;
    	// 页码逻辑处理
    	if(null!=page&&!"".equals(page)){
    		targetPage = Integer.parseInt(page);
    	}
    	int pageSize = 8;
    	EasyBuyUser easyBuyUser = new EasyBuyUser();
    	long maxRowsCount = easyBuyUserService.count(easyBuyUser);
    	PageUtil pageUtil = new PageUtil(pageSize, maxRowsCount); 
    	List<EasyBuyUser> users = easyBuyUserService.list(easyBuyUser, targetPage, pageSize);
    	Map<String, Object> results = new HashMap<String, Object>();
    	results.put("users", users);
    	results.put("targetPage", targetPage);
    	results.put("pageSize", pageSize);
    	results.put("maxCount", maxRowsCount);
    	results.put("maxPage",pageUtil.getMaxPage());
		return results;
	}
	/**
	 * 验证码是否正确验证
	 * @return
	 * @throws Exception
	 */
	private boolean codeIsMatch(HttpServletRequest request) throws Exception{
		// 页面输入验证码
		String code = request.getParameter("code");
		// 页面输入验证码与后台正确验证码比较
		if(code!=null&&code.trim().toLowerCase().equals(request.getSession().getAttribute("sRand").toString().toLowerCase())){
			return true;
			}	
		return false;
	}
	
	/**
	 * 验证用户名是否已存在
	 * @param request
	 * @return
	 */
	private boolean userIsExist(HttpServletRequest request){
		// 用户名
		String userName = request.getParameter("userName");
		// 用户名字符串非空判断和根据用户名获取用户非空判断
		if(userName != null && !userName.equals("")&&easyBuyUserService.getByName(userName)!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证用户名密码是否匹配
	 * @param request
	 * @return
	 */
	private EasyBuyUser isPass(HttpServletRequest request){
		// 用户名
		String userName = request.getParameter("userName");
		// 密码
		String passWord = request.getParameter("password");
		// 创建用户
		EasyBuyUser easyBuyUser = new EasyBuyUser();
		// 设置用户名
		easyBuyUser.setEuUserName(userName);
		// 设置密码
		easyBuyUser.setEuPassword(passWord);
		// 用户名密码非空判断，和根据用户名密码获取用户的非空判断
		if( null == userName || userName.trim().equals("")|| null== passWord || passWord.trim().equals("")){
			return null;
		}
		return easyBuyUserService.login(easyBuyUser);
	}
	
	private boolean isAdmin(HttpServletRequest request,EasyBuyUser easyBuyUser){
		if(easyBuyUser != null && easyBuyUser.getEuStatus()!=null && easyBuyUser.getEuStatus()==1){
			return true;
		}
		return false;
	}
	public IEasyBuyUserService getEasyBuyUserService() {
		return easyBuyUserService;
	}
	public void setEasyBuyUserService(IEasyBuyUserService easyBuyUserService) {
		this.easyBuyUserService = easyBuyUserService;
	}
}
