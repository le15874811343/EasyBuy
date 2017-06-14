package easybuy.service.impl;

import java.util.List;

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
	
	@Override
	public List<EasyBuyComment> list(EasyBuyComment easyBuyComment,int targetPage, int pageSize) {
		return easyBuyCommentDao.list(easyBuyComment, targetPage, pageSize);
	}

	@Override
	public long realCount(EasyBuyComment easyBuyComment) {
		return easyBuyCommentDao.realCount(easyBuyComment);
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

	@Override
	public EasyBuyComment getById(Long ecId) {
		return easyBuyCommentDao.getById(ecId);
	}

	@Override
	public int update(EasyBuyComment easyBuyComment) {
		return easyBuyCommentDao.update(easyBuyComment);
	}

	@Override
	public int delete(long ecId) {
		return easyBuyCommentDao.delete(ecId);
	}

}
