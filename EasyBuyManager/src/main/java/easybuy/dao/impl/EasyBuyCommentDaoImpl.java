package easybuy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.dao.IEasyBuyCommentDao;
import easybuy.pojo.EasyBuyComment;

@Repository
public class EasyBuyCommentDaoImpl extends BaseDao implements IEasyBuyCommentDao {

	@Override
	public List < EasyBuyComment> listByDate(int maxRows) {
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.EasyBuyCommentMapper.listByDate",maxRows);
	}

	@Override
	public int saveComment(EasyBuyComment easyBuyComment) {
		return super.getSqlSessionTemplate().insert("cn.com.pojo.mapping.EasyBuyCommentMapper.insertSelective", easyBuyComment);
	}

	@Override
	public long count() {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyCommentMapper.count");
	}

	@Override
	public List<EasyBuyComment> list(EasyBuyComment easyBuyComment,	int targetPage, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(easyBuyComment.getEcReply()!=null){
			params.put("ecReply", easyBuyComment.getEcReply());
		}
		params.put("max", targetPage*pageSize+1);
		params.put("min", (targetPage-1)*pageSize);
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.EasyBuyCommentMapper.list",params);
	}

	@Override
	public long realCount(EasyBuyComment easyBuyComment) {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyCommentMapper.realCount",easyBuyComment);
	}

	@Override
	public EasyBuyComment getById(Long ecId) {
	return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyCommentMapper.selectByPrimaryKey",ecId);
	}

	@Override
	public int update(EasyBuyComment easyBuyComment) {
		return super.getSqlSessionTemplate().update("cn.com.pojo.mapping.EasyBuyCommentMapper.updateByPrimaryKeySelective", easyBuyComment);
	}

	@Override
	public int delete(long ecId) {
		return super.getSqlSessionTemplate().delete("cn.com.pojo.mapping.EasyBuyCommentMapper.deleteByPrimaryKey", ecId);
	}

}
