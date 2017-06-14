package easybuy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyComment;

@Repository
public interface IEasyBuyCommentService {
	public List < EasyBuyComment> listByDate(int maxRows);
	
	public int saveComment(EasyBuyComment easyBuyComment);
	
	long count();
}
