package easybuy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyComment;

@Repository
public interface IEasyBuyCommentDao {
  
  /**
   * 根据日期降序获取留言集合
   * @param maxRows 最大记录条数
   * @return
   */
  List < EasyBuyComment> listByDate(int maxRows);
  
  int saveComment(EasyBuyComment easyBuyComment);
  
  long count();
  
  List<EasyBuyComment> list(EasyBuyComment easyBuyComment,int targetPage,int pageSize);
  
  long realCount(EasyBuyComment easyBuyComment);
  
  EasyBuyComment getById(Long ecId);
  
  int update(EasyBuyComment easyBuyComment);
  
  int delete(long ecId);
}
