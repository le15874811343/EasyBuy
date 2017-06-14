package easybuy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.ProductCategory;
@Repository
public interface IProductCategoryDao {
	/**
	 * 获取一级分类
	 * @return
	 */
	List<ProductCategory> listParentCategory();
    
    /**
	 * 获取二级分类
	 * @return
	 */
	List<ProductCategory> listChildCategory();
    
    List<ProductCategory> list (int targetPage,int pageSize);
    
    List<ProductCategory> listByParentId(long parentId);
    
    long count();
    
    ProductCategory getById(long epcId);
    
    int update(ProductCategory productCategory);
    
    int add(ProductCategory productCategory);
    
    int deleteByEpcId(long epcId);
    
    int deleteByParentId(long parentId);
}
