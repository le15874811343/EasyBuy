package easybuy.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		boolean  flag=true;
		 String url = arg0.getRequestURI(); 
         if(url.indexOf("category")>0||url.indexOf("comment")>0||url.indexOf("news")>0||url.indexOf("order")>0||url.indexOf("product")>0||url.indexOf("user")>0||url.indexOf("image")>0){
        	 flag=false;
         }
         if(url.indexOf("login")>0||url.indexOf("match")>0){
        	 flag=true;
         }
		HttpSession  session=arg0.getSession();
		Object obj=session.getAttribute("user");
		if(obj!=null)
		{
			flag=true;
		}
		if(url.equals("/EasyBuyManager/login.html")){
			flag=true;
		}
	    if(!flag){
	    	arg1.sendRedirect("/EasyBuyManager/login.html");  
	    }
		return flag;
	}

}
