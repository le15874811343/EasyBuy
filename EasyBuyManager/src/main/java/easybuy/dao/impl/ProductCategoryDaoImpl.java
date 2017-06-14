package easybuy.dao.impl;

import java.util.HashMap;
import java.util.List;
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
	public List<ProductCategory> listParentCategory() {
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.ProductCategoryMapper.listParentCategory");
	}
	
	/**
	 * 获取二级分类
	 * @return
	 */
	@Override
	public List<ProductCategory> listChildCategory() {
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.ProductCategoryMapper.listChildCategory");
	}

	@Override
    public	List<ProductCategory> list(int targetPage, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("max", targetPage*pageSize+1);
		params.put("min", (targetPage-1)*pageSize);
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.ProductCategoryMapper.list",params);
	}

	@Override
	public long count() {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.ProductCategoryMapper.count");
	}

	@Override
	public ProductCategory getById(long epcId) {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.ProductCategoryMapper.selectByPrimaryKey",epcId);
	}

	@Override
	public int update(ProductCategory productCategory) {
		return super.getSqlSessionTemplate().update("cn.com.pojo.mapping.ProductCategoryMapper.updateByPrimaryKeySelective", productCategory);
	}

	@Override
	public int add(ProductCategory productCategory) {
		return super.getSqlSessionTemplate().insert("cn.com.pojo.mapping.ProductCategoryMapper.insertSelective", productCategory);
	}

	@Override
	public int deleteByEpcId(long epcId) {
		return super.getSqlSessionTemplate().delete("cn.com.pojo.mapping.ProductCategoryMapper.deleteByPrimaryKey", epcId);
	}

	@Override
	public int deleteByParentId(long parentId) {
		return super.getSqlSessionTemplate().delete("cn.com.pojo.mapping.ProductCategoryMapper.deleteByParentId", parentId);
	}

	@Override
	public List<ProductCategory> listByParentId(long parentId) {
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.ProductCategoryMapper.listByParentId",parentId);
	}
	
	

}
