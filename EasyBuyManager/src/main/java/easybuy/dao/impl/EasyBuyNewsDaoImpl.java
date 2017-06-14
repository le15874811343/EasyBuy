package easybuy.dao.impl;

import java.util.HashMap;
import java.util.List;
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

	@Override
	public List<EasyBuyNews> list(EasyBuyNews easyBuyNews, int targetPage,int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(easyBuyNews.getEnType()!=null){
			params.put("enType", easyBuyNews.getEnType());
		}
		params.put("max", targetPage*pageSize+1);
		params.put("min", (targetPage-1)*pageSize);
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.EasyBuyNewsMapper.list", params);
	}

	@Override
	public long count(EasyBuyNews easyBuyNews) {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyNewsMapper.count", easyBuyNews);
	}

	@Override
	public int save(EasyBuyNews easyBuyNews) {
		return super.getSqlSessionTemplate().insert("cn.com.pojo.mapping.EasyBuyNewsMapper.insertSelective", easyBuyNews);
	}

	@Override
	public int update(EasyBuyNews easyBuyNews) {
		return super.getSqlSessionTemplate().update("cn.com.pojo.mapping.EasyBuyNewsMapper.updateByPrimaryKeySelective", easyBuyNews);
	}

	@Override
	public int delete(long enId) {
		return super.getSqlSessionTemplate().delete("cn.com.pojo.mapping.EasyBuyNewsMapper.deleteByPrimaryKey", enId);
	}

}
