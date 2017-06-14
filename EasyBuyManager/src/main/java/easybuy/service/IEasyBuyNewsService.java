package easybuy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyNews;
@Repository
public interface IEasyBuyNewsService {
	/**
	 * 
     * @param maxRows 最大记录数
	 * @param type 类型 0 公告 1新闻
     * @return
     */
	  Map<Long, EasyBuyNews> listByDate(int maxRows,int type);
	  
	  /**
	   * 根据新闻编号查询新闻详情
	   * @param enId
	   * @return
	   */
	  EasyBuyNews listByEnId(Long enId);
	  
	  public List<EasyBuyNews> list(EasyBuyNews easyBuyNews, int targetPage,int pageSize);
	  
	  public long count(EasyBuyNews easyBuyNews);
	  
	  public int save(EasyBuyNews easyBuyNews);
	  
	  public int update(EasyBuyNews easyBuyNews);
	  
	  public int delete(long enId);
}
