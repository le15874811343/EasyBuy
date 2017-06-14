package easybuy.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyComment;

@Repository
public interface IEasyBuyCommentService {
	public List < EasyBuyComment> listByDate(int maxRows);
	
	public int saveComment(EasyBuyComment easyBuyComment);
	
	long count();
	
	public List<EasyBuyComment> list(EasyBuyComment easyBuyComment,	int targetPage, int pageSize);
	
	public long realCount(EasyBuyComment easyBuyComment);
	
	public EasyBuyComment getById(Long ecId);
	
	public int update(EasyBuyComment easyBuyComment);
	
	public int delete(long ecId);
}
