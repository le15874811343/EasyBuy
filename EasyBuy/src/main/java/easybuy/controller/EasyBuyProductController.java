package easybuy.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import easybuy.pojo.EasyBuyProduct;
import easybuy.service.IEasyBuyProductService;
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
	
	/**
	 * 进入商品详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view.html")
	public String goProductView(HttpServletRequest request){
		// 商品id
		String epId = request.getParameter("epId");
		// 非空判断
		if (null == epId || "".equals(epId.trim())){
			return "product-view";
		}
		// 获取商品
		EasyBuyProduct productDea = productService.getById(Long.parseLong(epId));
		request.setAttribute("productDea", productDea);
		request.setAttribute("epcChildId", productDea.getEpcChildId());
		request.setAttribute("epcId", productDea.getEpcId());
		return "product-view";
	}
	
	/**
	 * 进入商品列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html")
	public String goProductList(HttpServletRequest request){
		// 目标页参数处理
		int targetPage = 1;
		String tarPage = request.getParameter("targetPage");
		if(null != tarPage && !"".equals(tarPage.trim())){
			targetPage = Integer.parseInt(tarPage);
		}
		// 每页显示数量
		int pageSize = 12;
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
		PageUtil pageUtil = new PageUtil(12, maxRowsCount);
		// 获取商品集合
	    Map<Long, EasyBuyProduct> products = productService.listByChildId(epRebate,epcId,epcChildId, targetPage, pageSize);
	    request.setAttribute("targetPage", targetPage);
	    request.setAttribute("maxPage", pageUtil.getMaxPage());
	    request.setAttribute("products",products);
	    request.setAttribute("maxCount", maxRowsCount);
	    request.setAttribute("epcChildId", epcChildId);
	    if(_epRebate != null && !"".equals(_epRebate.trim())){
	    	epcId=(long) 0;
		}
	    request.setAttribute("epcId", epcId);
	    request.setAttribute("epRebate", epRebate);
		return "product-list";
	}
	public IEasyBuyProductService getProductService() {
		return productService;
	}
	public void setProductService(IEasyBuyProductService productService) {
		this.productService = productService;
	}
	
	
}
