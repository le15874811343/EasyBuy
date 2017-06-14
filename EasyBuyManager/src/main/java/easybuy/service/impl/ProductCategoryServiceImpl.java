package easybuy.service.impl;

import java.util.List;

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
	public List<ProductCategory> listParentCategory() {
		return productCategoryDao.listParentCategory();
	}
	/**
	 * 获取二级分类
	 * @return
	 */
	@Override
	public List<ProductCategory> listChildCategory() {
		return productCategoryDao.listChildCategory();
	}
	@Override
	public List<ProductCategory> list(int targetPage, int pageSize) {
		return productCategoryDao.list(targetPage, pageSize);
	}
	@Override
	public long count() {
		return productCategoryDao.count();
	}
	
	@Override
	public ProductCategory getById(long epcId) {
		return productCategoryDao.getById(epcId);
	}
	
	@Override
	public int update(ProductCategory productCategory) {
		return this.productCategoryDao.update(productCategory);
	}
	
	@Override
	public int add(ProductCategory productCategory) {
		return productCategoryDao.add(productCategory);
	}
	
	@Override
	public int deleteByEpcId(long epcId) {
		return productCategoryDao.deleteByEpcId(epcId);
	}
	@Override
	public int deleteByParentId(long parentId) {
		return productCategoryDao.deleteByParentId(parentId);
	}
	
	@Override
	public List<ProductCategory> listByParentId(long parentId) {
		return productCategoryDao.listByParentId(parentId);
	}
	
	public IProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}
	public void setProductCategoryDao(IProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}
	

	
}
