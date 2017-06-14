package easybuy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.ProductCategory;

@Repository
public interface IProductCategoryService {
	/**
	 * 获取一级分类
	 * @return
	 */
	List<ProductCategory>  listParentCategory();
	
	/**
	 * 获取二级分类
	 * @return
	 */
	public List<ProductCategory> listChildCategory();
	
	public List<ProductCategory> list(int targetPage, int pageSize);
	
	public long count();
	
	public ProductCategory getById(long epcId);
	
	public int update(ProductCategory productCategory);
	
	public int add(ProductCategory productCategory);
	
	public int deleteByEpcId(long epcId);
	
	 int deleteByParentId(long parentId);
	 
	 List<ProductCategory> listByParentId(long parentId);
}
