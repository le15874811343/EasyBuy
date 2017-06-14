package easybuy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyNews;

@Repository
public interface IEasyBuyNewsDao {
  /**
   * 
   * @param maxRows 最大记录数
   * @param type 类型 0 活动 1新闻
   * @return
   */
  Map<Long, EasyBuyNews> listByDate(int maxRows,int type);
  
  /**
   * 根据新闻编号查询新闻详情
   * @param enId
   * @return
   */
  EasyBuyNews listByEnId(Long enId);
  
  List<EasyBuyNews> list(EasyBuyNews easyBuyNews,int targetPage,int pageSize);
  
  long count(EasyBuyNews easyBuyNews);
  
  int save(EasyBuyNews easyBuyNews);
  
  int update(EasyBuyNews easyBuyNews);
  
  int delete(long enId);
}
