package easybuy.interceptor;



import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import easybuy.dao.IProductCategoryDao;
import easybuy.dao.impl.ProductCategoryDaoImpl;
import easybuy.pojo.ProductCategory;
import easybuy.service.IProductCategoryService;

public class LoginInterceptor implements HandlerInterceptor {

	@Resource
	private IProductCategoryService productCategoryService;
	
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
		 if(url.indexOf("/order")>=0||url.indexOf("/comment")>=0||url.indexOf("/shopping")>=0){
			 flag=false;
		 }
		HttpSession  session=arg0.getSession();
		 if(session.getAttribute("parentCategorys")==null||session.getAttribute("childCategorys")==null){
			// 一级分类
		    Map<Long,ProductCategory> parentCategorys =  productCategoryService.listParentCategory();
		    // 二级分类
		    Map<Long,ProductCategory> childCategorys = productCategoryService.listChildCategory();
			session.setAttribute("parentCategorys", parentCategorys);
			session.setAttribute("childCategorys", childCategorys);
		 }
		Object obj=session.getAttribute("user");
		if(obj!=null)
		{
			flag=true;
		}
		if(url.equals("/EasyBuy/main/welcome.html")){
			flag=true;
		}
	    if(!flag){
	    	arg1.sendRedirect("/EasyBuy/main/welcome.html");  
	    }
		return flag;
	}

	public IProductCategoryService getProductCategoryService() {
		return productCategoryService;
	}

	public void setProductCategoryService(
			IProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	
}
