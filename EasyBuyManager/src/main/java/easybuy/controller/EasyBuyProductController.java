package easybuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.dao.IProductCategoryDao;
import easybuy.pojo.EasyBuyProduct;
import easybuy.pojo.ProductCategory;
import easybuy.service.IEasyBuyProductService;
import easybuy.service.IProductCategoryService;
import easybuy.util.PageUtil;

/**
 * 商品控制器
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/product")
public class EasyBuyProductController {

	@Resource
	private IEasyBuyProductService productService;
	
	@Resource
	private IProductCategoryService productCategoryService;
	/**
	 * 进入商品详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyView")
	@ResponseBody
	public Map<String, Object> goProductView(HttpServletRequest request){
		Map<String, Object> results = new HashMap<String, Object>();
		// 商品id
		String epId = request.getParameter("epId");
		// 获取商品
		EasyBuyProduct productDea = productService.getById(Long.parseLong(epId));
	    List<ProductCategory> parents =	productCategoryService.listParentCategory();
		results.put("productDea", productDea);
		results.put("parents", parents);
		return results;
	}
	
	/**
	 * 进入商品列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> goProductList(HttpServletRequest request){
		 Map<String, Object> results = new HashMap<String, Object>();
		// 目标页参数处理
		int targetPage = 1;
		String tarPage = request.getParameter("targetPage");
		if(null != tarPage && !"".equals(tarPage.trim())){
			targetPage = Integer.parseInt(tarPage);
		}
		// 每页显示数量
		int pageSize = 8;
		// 二级分类Id
		Long epcChildId = null;
		String ChildId = request.getParameter("epcChildId");
		if(ChildId != null && !"".equals(ChildId.trim())){
			epcChildId = Long.parseLong(ChildId);
		}
		// 一级分类Id
		Long epcId = null;
		String _epcId = request.getParameter("epcId");
		if(_epcId != null && !"".equals(_epcId.trim())){
			epcId = Long.parseLong(_epcId);
		}
		// 折扣
		Double epRebate = null;
		String _epRebate = request.getParameter("epRebate");
		if(_epRebate != null && !"".equals(_epRebate.trim())){
			epRebate = Double.parseDouble(_epRebate);
		}
		// 纪录条数
		Long maxRowsCount = productService.countByChildId(epRebate,epcId,epcChildId);
		PageUtil pageUtil = new PageUtil(pageSize, maxRowsCount);
		// 获取商品集合
		List<EasyBuyProduct> products = productService.listByChildId(epRebate,epcId,epcChildId, targetPage, pageSize);
		results.put("products", products);
	  	results.put("targetPage", targetPage);
	  	results.put("pageSize", pageSize);
	  	results.put("maxCount", maxRowsCount);
	  	results.put("maxPage",pageUtil.getMaxPage());
	  	return results;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public int addProduct(HttpServletRequest request){
		EasyBuyProduct easyBuyProduct = new EasyBuyProduct();
		String epName = request.getParameter("epName");
		String epDescription = request.getParameter("epDescription");
		String epPrice = request.getParameter("epPrice");
		String epStock = request.getParameter("epStock");
		String epcId = request.getParameter("epcId");
		String epcChildId = request.getParameter("epcChildId");
		String epFileName = request.getParameter("epFileName");
		String epRebate = request.getParameter("epRebate");
		easyBuyProduct.setEpName(epName);
		easyBuyProduct.setEpDescription(epDescription);
		easyBuyProduct.setEpPrice(Double.parseDouble(epPrice));
		easyBuyProduct.setEpStock(Long.parseLong(epStock));
		easyBuyProduct.setEpcId(Long.parseLong(epcId));
		easyBuyProduct.setEpcChildId(Long.parseLong(epcChildId));
		easyBuyProduct.setEpFileName("tepimages/"+epFileName);
		easyBuyProduct.setEpRebate(Double.parseDouble(epRebate));
		if(productService.save(easyBuyProduct)>0){
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int updateProduct(HttpServletRequest request){
		EasyBuyProduct easyBuyProduct = new EasyBuyProduct();
		String epId = request.getParameter("epId");
		String epName = request.getParameter("epName");
		String epDescription = request.getParameter("epDescription");
		String epPrice = request.getParameter("epPrice");
		String epStock = request.getParameter("epStock");
		String epcId = request.getParameter("epcId");
		String epcChildId = request.getParameter("epcChildId");
		String epFileName = request.getParameter("epFileName");
		String epRebate = request.getParameter("epRebate");
		easyBuyProduct.setEpId(Long.parseLong(epId));
		easyBuyProduct.setEpName(epName);
		easyBuyProduct.setEpDescription(epDescription);
		easyBuyProduct.setEpPrice(Double.parseDouble(epPrice));
		easyBuyProduct.setEpStock(Long.parseLong(epStock));
		easyBuyProduct.setEpcId(Long.parseLong(epcId));
		easyBuyProduct.setEpcChildId(Long.parseLong(epcChildId));
		if(epFileName!=null && !"".equals(epFileName)){
			easyBuyProduct.setEpFileName("tepimages/"+epFileName);	
		}
		easyBuyProduct.setEpRebate(Double.parseDouble(epRebate));
		if(productService.update(easyBuyProduct)>0){
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(HttpServletRequest request){
		String epId = request.getParameter("epId");
		if(productService.delete(Long.parseLong(epId))>0){
			return 1;
		}
		return 0;
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
	
	
}
