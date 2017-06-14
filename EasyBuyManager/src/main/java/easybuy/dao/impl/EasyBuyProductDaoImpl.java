package easybuy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.dao.IEasyBuyProductDao;
import easybuy.pojo.EasyBuyProduct;

@Repository
public class EasyBuyProductDaoImpl extends BaseDao implements IEasyBuyProductDao {
	/**
	 * 根据折扣率升序获取商品集合
	 * @param maxRowsNumber 获取最大记录数
	 * @return
	 */
	@Override
	public  Map<Long, EasyBuyProduct> listByRebate(int maxRowsNumber) {
	 Map<Long, EasyBuyProduct>	productMap = super.getSqlSessionTemplate().selectMap("easybuy.pojo.mapping.EasyBuyProductMapper.listByRebate", maxRowsNumber, "epId");
	 return productMap;
	}

	/**
	 * 根据销售额降序获取商品集合
     * @param maxRowsNumber 获取最大记录数
	 * @return
	 */
	@Override
	public Map<Long, EasyBuyProduct> listBySaleCount(int maxRowsNumber) {
	 Map<Long, EasyBuyProduct>	productMap = super.getSqlSessionTemplate().selectMap("easybuy.pojo.mapping.EasyBuyProductMapper.listBySaleCount", maxRowsNumber, "epId");
	 return productMap;
	}

	/**
	 * 根据商品编号获取商品详情
	 * @param epId 商品编号
	 * @return 
	 */
	@Override
	public EasyBuyProduct getById(Long epId) {
		return super.getSqlSessionTemplate().selectOne("easybuy.pojo.mapping.EasyBuyProductMapper.selectByPrimaryKey", epId);
	}

	/**
	  * 根据商品二级分类获取商品详情
	  * @param epcChildId 二级分类编号
	  * @param targetPage 当前页
	  * @param pageSize 每页最大数
	  * @return
	  */
	@Override
	public List<EasyBuyProduct> listByChildId(Double epRebate,Long epcId,Long epcChildId,int targetPage, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != epcChildId && 0!=epcChildId.longValue()){
			params.put("epcChildId", epcChildId);
		}
		if(null != epcId && 0!=epcId.longValue()){
			params.put("epcId", epcId);
		}
		if(null != epRebate && 0!=epRebate.doubleValue()){
			params.put("epRebate", epRebate);
		}
		params.put("maxNum", targetPage*pageSize+1);
		params.put("minNum", (targetPage-1)*pageSize);
		return super.getSqlSessionTemplate().selectList("easybuy.pojo.mapping.EasyBuyProductMapper.listByChildId", params);
	}

	/**
	  * 根据商品二级分类获取商品数量
	  * @param epcChildId 二级分类编号
	  * @return
	  */
	@Override
	public Long countByChildId(Double epRebate,Long epcId,Long epcChildId) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != epcChildId && 0!=epcChildId.longValue()){
			params.put("epcChildId", epcChildId);
		}
		if(null != epcId && 0!=epcId.longValue()){
			params.put("epcId", epcId);
		}
		if(null != epRebate && 0!=epRebate.doubleValue()){
			params.put("epRebate", epRebate);
		}
		return super.getSqlSessionTemplate().selectOne("easybuy.pojo.mapping.EasyBuyProductMapper.countByChildId",params);
	}

	@Override
	public int update(EasyBuyProduct easyBuyProduct) {
		return super.getSqlSessionTemplate().update("easybuy.pojo.mapping.EasyBuyProductMapper.updateByPrimaryKeySelective",easyBuyProduct);
	}

	@Override
	public int save(EasyBuyProduct easyBuyProduct) {
		return super.getSqlSessionTemplate().insert("easybuy.pojo.mapping.EasyBuyProductMapper.insertSelective", easyBuyProduct);
	}

	@Override
	public int delete(Long epId) {
		return super.getSqlSessionTemplate().delete("easybuy.pojo.mapping.EasyBuyProductMapper.deleteByPrimaryKey", epId);
	}

}
