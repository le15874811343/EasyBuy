package easybuy.service;

import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.ProductCategory;

@Repository
public interface IProductCategoryService {
	/**
	 * 获取一级分类
	 * @return
	 */
	public Map<Long, ProductCategory> listParentCategory();
	
	/**
	 * 获取二级分类
	 * @return
	 */
	public Map<Long, ProductCategory> listChildCategory();
}
