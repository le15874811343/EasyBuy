package easybuy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import easybuy.dao.IEasyBuyOrderDao;
import easybuy.pojo.EasyBuyOrder;
import easybuy.pojo.vo.EasyBuyOrderVo;
import easybuy.service.IEasyBuyOrderService;

@Service
public class EasyBuyOrderServiceImpl implements IEasyBuyOrderService {
  @Resource
  private IEasyBuyOrderDao easyBuyOrderDao;

public IEasyBuyOrderDao getEasyBuyOrderDao() {
	return easyBuyOrderDao;
}

public void setEasyBuyOrderDao(IEasyBuyOrderDao easyBuyOrderDao) {
	this.easyBuyOrderDao = easyBuyOrderDao;
}

@Override
public int saveOrder(EasyBuyOrder easyBuyOrder) {
	return easyBuyOrderDao.saveOrder(easyBuyOrder);
}

@Override
public  List< EasyBuyOrderVo> listVo(String status,Long eoId, Long userId,String userName, int targetPage,	int pageSize) {
	return easyBuyOrderDao.listVo(status,eoId, userId, userName,targetPage, pageSize);
}

@Override
public long count(String status,Long eoId, Long userId,String userName) {
	return easyBuyOrderDao.count(status,eoId, userId,userName);
}

@Override
public long countAll() {
	return easyBuyOrderDao.countAll();
}

@Override
public int updateStatus(EasyBuyOrder easyBuyOrder) {
	return easyBuyOrderDao.updateStatus(easyBuyOrder);
}

@Override
public int delete(Long eoId) {
	return easyBuyOrderDao.delete(eoId);
}
	
}
