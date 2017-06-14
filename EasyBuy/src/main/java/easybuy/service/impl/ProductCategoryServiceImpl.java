package easybuy.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import easybuy.dao.IProductCategoryDao;
import easybuy.pojo.ProductCategory;
import easybuy.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService{
	@Resource
	private IProductCategoryDao productCategoryDao;
	/**
	 * 获取一级分类
	 * @return
	 */
	@Override
	public Map<Long, ProductCategory> listParentCategory() {
		return productCategoryDao.listParentCategory();
	}
	/**
	 * 获取二级分类
	 * @return
	 */
	@Override
	public Map<Long, ProductCategory> listChildCategory() {
		return productCategoryDao.listChildCategory();
	}
	public IProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}
	public void setProductCategoryDao(IProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}
 
	
}
