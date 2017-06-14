package easybuy.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.dao.IProductCategoryDao;
import easybuy.pojo.ProductCategory;

@Repository
public class ProductCategoryDaoImpl extends BaseDao implements IProductCategoryDao{
	/**
	 * 获取一级分类
	 * @return
	 */
	@Override
	public Map<Long, ProductCategory> listParentCategory() {
		return super.getSqlSessionTemplate().selectMap("cn.com.pojo.mapping.ProductCategoryMapper.listParentCategory", "epcId");
	}
	
	/**
	 * 获取二级分类
	 * @return
	 */
	@Override
	public Map<Long, ProductCategory> listChildCategory() {
		return super.getSqlSessionTemplate().selectMap("cn.com.pojo.mapping.ProductCategoryMapper.listChildCategory", "epcId");
	}

}
