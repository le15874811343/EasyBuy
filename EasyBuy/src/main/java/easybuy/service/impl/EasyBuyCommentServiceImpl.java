package easybuy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import easybuy.dao.IEasyBuyCommentDao;
import easybuy.pojo.EasyBuyComment;
import easybuy.service.IEasyBuyCommentService;

@Service
public class EasyBuyCommentServiceImpl implements IEasyBuyCommentService{
 @Resource
 private IEasyBuyCommentDao easyBuyCommentDao;
	@Override
	public List < EasyBuyComment> listByDate(int maxRows) {
		return easyBuyCommentDao.listByDate(maxRows);
	}
	
	@Override
	public int saveComment(EasyBuyComment easyBuyComment) {
		return easyBuyCommentDao.saveComment(easyBuyComment);
	}
	
	public IEasyBuyCommentDao getEasyBuyCommentDao() {
		return easyBuyCommentDao;
	}
	public void setEasyBuyCommentDao(IEasyBuyCommentDao easyBuyCommentDao) {
		this.easyBuyCommentDao = easyBuyCommentDao;
	}

	@Override
	public long count() {
		return easyBuyCommentDao.count();
	}

  
}
