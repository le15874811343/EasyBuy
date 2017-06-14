package easybuy.service.impl;

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

	
}
