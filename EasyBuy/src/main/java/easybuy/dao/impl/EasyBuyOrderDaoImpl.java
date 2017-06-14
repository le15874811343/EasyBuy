package easybuy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sun.org.apache.regexp.internal.recompile;

import easybuy.dao.IEasyBuyOrderDao;
import easybuy.pojo.EasyBuyOrder;
import easybuy.pojo.vo.EasyBuyOrderVo;

@Repository
public class EasyBuyOrderDaoImpl extends BaseDao implements IEasyBuyOrderDao {

	@Override
	public int saveOrder(EasyBuyOrder easyBuyOrder) {
		return super.getSqlSessionTemplate().insert("cn.com.pojo.mapping.EasyBuyOrderMapper.insertSelective", easyBuyOrder);
	}

	@Override
	public  List< EasyBuyOrderVo> listVo(String status,Long eoId, Long userId,	int targetPage, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != eoId && eoId.longValue()!=0){
			params.put("eoId", eoId);
		}
		if(null != userId && userId.longValue()!=0){
			params.put("userId", userId);
		}
		if(null != status && !"".equals(status)){
			params.put("status", status);
		}
		params.put("max", targetPage*pageSize+1);
		params.put("min", (targetPage-1)*pageSize);
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.EasyBuyOrderMapper.listVo", params);
	}

	@Override
	public long count(String status ,Long eoId, Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != eoId && eoId.longValue()!=0){
			params.put("eoId", eoId);
		}
		if(null != userId && userId.longValue()!=0){
			params.put("userId", userId);
		}
		if(null != status && !"".equals(status)){
			params.put("status", status);
		}
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyOrderMapper.count", params);

	}

	@Override
	public long countAll() {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyOrderMapper.countall");
	}

	@Override
	public int updateStatus(EasyBuyOrder easyBuyOrder) {
		return super.getSqlSessionTemplate().update("cn.com.pojo.mapping.EasyBuyOrderMapper.updateStatus",easyBuyOrder);
	}

}
