package easybuy.dao.impl;

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

}
