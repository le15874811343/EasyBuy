package easybuy.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.dao.IEasyBuyNewsDao;
import easybuy.pojo.EasyBuyNews;

@Repository
public class EasyBuyNewsDaoImpl extends BaseDao implements IEasyBuyNewsDao {

	 /**
	   * 
	   * @param maxRows 最大记录数
	   * @param type 类型 0 活动 1新闻
	   * @return
	   */
	@Override
	public Map<Long, EasyBuyNews> listByDate(int maxRows, int type) {
		Map<String, Integer> params =new HashMap<String, Integer>();
		params.put("enType", type);
		params.put("maxRows", maxRows);
		return super.getSqlSessionTemplate().selectMap("cn.com.pojo.mapping.EasyBuyNewsMapper.listByDate", params, "enId");
	}

	  /**
	   * 根据新闻编号查询新闻详情
	   * @param enId
	   * @return
	   */
	@Override
	public EasyBuyNews listByEnId(Long enId) {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyNewsMapper.selectByPrimaryKey", enId);
	}

}
