package easybuy.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.ProductCategory;
@Repository
public interface IProductCategoryDao {
	/**
	 * 获取一级分类
	 * @return
	 */
    Map<Long, ProductCategory> listParentCategory();
    
    /**
	 * 获取二级分类
	 * @return
	 */
    Map<Long, ProductCategory> listChildCategory();
}
