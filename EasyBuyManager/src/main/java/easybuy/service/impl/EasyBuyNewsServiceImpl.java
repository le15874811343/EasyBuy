package easybuy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import easybuy.dao.IEasyBuyNewsDao;
import easybuy.pojo.EasyBuyNews;
import easybuy.service.IEasyBuyNewsService;
@Service
public class EasyBuyNewsServiceImpl implements IEasyBuyNewsService{

	@Resource
	private IEasyBuyNewsDao easyBuyNewsDao;
	@Override
	public Map<Long, EasyBuyNews> listByDate(int maxRows, int type) {
		return easyBuyNewsDao.listByDate(maxRows, type);
	}
	public IEasyBuyNewsDao getEasyBuyNewsDao() {
		return easyBuyNewsDao;
	}
	public void setEasyBuyNewsDao(IEasyBuyNewsDao easyBuyNewsDao) {
		this.easyBuyNewsDao = easyBuyNewsDao;
	}
	
	/**
	   * 根据新闻编号查询新闻详情
	   * @param enId
	   * @return
	   */
	@Override
	public EasyBuyNews listByEnId(Long enId) {
		return easyBuyNewsDao.listByEnId(enId);
	}
	@Override
	public List<EasyBuyNews> list(EasyBuyNews easyBuyNews, int targetPage,int pageSize) {
		return easyBuyNewsDao.list(easyBuyNews, targetPage, pageSize);
	}
	@Override
	public long count(EasyBuyNews easyBuyNews) {
		return easyBuyNewsDao.count(easyBuyNews);
	}
	
	@Override
	public int save(EasyBuyNews easyBuyNews) {
		return easyBuyNewsDao.save(easyBuyNews);
	}
	@Override
	public int update(EasyBuyNews easyBuyNews) {
		return easyBuyNewsDao.update(easyBuyNews);
	}
	@Override
	public int delete(long enId) {
		return easyBuyNewsDao.delete(enId);
	}
	
	
}
