package easybuy.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import easybuy.pojo.EasyBuyUser;
import easybuy.service.IEasyBuyUserService;
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
	@RequestMapping(value = "/login.html")
	public String login(HttpServletRequest request,String url){
		// 用户名
		String userName = request.getParameter("userName");
		// 密码
		String password = request.getParameter("password");
		// 非空判断
		if(null == userName  || "".equals(userName.trim())||null == password || "".equals(password.trim())){
			request.setAttribute("error", "录入信息有误,请重新输入");
			return "/login";
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
		if(loginUser != null){
			request.getSession().setAttribute("user", loginUser);
			return "redirect:"+url;
		} else {
			request.setAttribute("notMatch", "用户名或密码输入错误,请重新输入");
			return "/login";
		}
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/reg.html")
	public String reg(HttpServletRequest request){
		// 用户名
		String userName = request.getParameter("userName");
		// 密码
		String password = request.getParameter("password");
		// 非空判断
		if(null == userName  || "".equals(userName.trim())||null == password || "".equals(password.trim())){
			request.setAttribute("error", "录入信息有误,请重新输入");
			return "/register";
		}
		// 创建用户
		EasyBuyUser saveUser = new EasyBuyUser();
		// 设置用户名
		saveUser.setEuUserName(userName);
		// 设置密码
		saveUser.setEuPassword(password);
		// 保存
		saveUser = easyBuyUserService.saveUser(saveUser);
		if(saveUser != null){
			// 注册即自动登陆
			request.getSession().setAttribute("user", saveUser);
			return "redirect:reg-result";
		} else {
			request.setAttribute("notMatch", "数据错误，注册失败");
			return "/register";
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
				if(isPass(request)){
				// 通过
				response.getWriter().print("1");
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
	
	/**
	 * 注册匹配比较
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/regmatch.html")
	public void regIsMatch(HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		// 验证码验证
		if(codeIsMatch(request)){
			// 用户名重复验证
			if(userIsExist(request)){
				// 用户名已存在
				response.getWriter().print("2");
			}else{
				// 通过
				response.getWriter().print("1");
			}
		}else{
			 // 验证码错误
			 response.getWriter().print("0");
		}
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
	// 创建用户
	EasyBuyUser easyBuyUser = new EasyBuyUser();
	// 获取当前登录用户
	EasyBuyUser sessionEu = (EasyBuyUser)request.getSession().getAttribute("user");
	// 设置用户名
	easyBuyUser.setEuUserName(sessionEu.getEuUserName());
	// 设置用户Id
	easyBuyUser.setEuUserId(sessionEu.getEuUserId());
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
		request.getSession().setAttribute("user", easyBuyUser);
		response.getWriter().print("1");
	}
	response.getWriter().flush();//清空缓存,刷新
	response.getWriter().close();
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
	private boolean isPass(HttpServletRequest request){
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
		if( null != userName && !userName.trim().equals("")&& null!= passWord && !passWord.equals("")&&easyBuyUserService.login(easyBuyUser)!=null){
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
