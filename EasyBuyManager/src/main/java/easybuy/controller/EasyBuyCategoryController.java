package easybuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.pojo.ProductCategory;
import easybuy.service.IProductCategoryService;
import easybuy.util.PageUtil;

@Controller
@RequestMapping("/category")
public class EasyBuyCategoryController {
	@Resource
    private IProductCategoryService categoryService;
	
	@RequestMapping(value="/list")
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
    	long maxRowsCount = categoryService.count();
    	PageUtil pageUtil = new PageUtil(pageSize, maxRowsCount);
    	List<ProductCategory> categorys = categoryService.list(targetPage, pageSize);
    	Map<String, Object> results = new HashMap<String, Object>();
    	results.put("categorys", categorys);
    	results.put("targetPage", targetPage);
    	results.put("pageSize", pageSize);
    	results.put("maxCount", maxRowsCount);
    	results.put("maxPage",pageUtil.getMaxPage());
		return results;
	}
	
	@RequestMapping("/modifyView")
	@ResponseBody
	public Map<String, Object> modifyView(HttpServletRequest request){
		 Map<String, Object> result = new HashMap<String, Object>();
		 String epcId = request.getParameter("epcId");
		 ProductCategory category = categoryService.getById(Long.parseLong(epcId));
		 List<ProductCategory> parents = categoryService.listParentCategory();
		 result.put("parents", parents);
		 result.put("category", category);
		return result;
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public int update(HttpServletRequest request){
		String epcId = request.getParameter("epcId");
		String parentId = request.getParameter("parentId");
		String epcName = request.getParameter("epcName");
		ProductCategory productCategory = new ProductCategory();
		productCategory.setEpcId(Long.parseLong(epcId));
		productCategory.setEpcName(epcName);
		productCategory.setEpcParentId(Long.parseLong(parentId));
		if(categoryService.update(productCategory)>0){
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/addView")
	@ResponseBody
	public Map<String,Object> addView(){
		Map<String, Object> results = new HashMap<String, Object>();
		List<ProductCategory> parents  = categoryService.listParentCategory();
		results.put("parents", parents);
		return results;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public int add(HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		String epcName = request.getParameter("epcName");
		ProductCategory productCategory = new ProductCategory();
		productCategory.setEpcName(epcName);
		productCategory.setEpcParentId(Long.parseLong(parentId));
		if(categoryService.add(productCategory)>0){
			return 1;
		}
		return 0;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(HttpServletRequest request){
		String epcId = request.getParameter("epcId");
		try{
			categoryService.deleteByEpcId(Long.parseLong(epcId));
			categoryService.deleteByParentId(Long.parseLong(epcId));
			return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@RequestMapping("/childList")
	@ResponseBody
	public List<ProductCategory> childList(HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		return categoryService.listByParentId(Long.parseLong(parentId));
	}
	
	@RequestMapping("/parentList")
	@ResponseBody
	public List<ProductCategory> parentList(HttpServletRequest request){
		return categoryService.listParentCategory();
	}
	
	public IProductCategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(IProductCategoryService categoryService) {
		this.categoryService = categoryService;
	}

	
}
