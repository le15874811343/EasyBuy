package easybuy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import easybuy.dao.IEasyBuyUserDao;
import easybuy.pojo.EasyBuyUser;
import easybuy.service.IEasyBuyUserService;

@Service
public class EasyBuyUserServiceImpl implements IEasyBuyUserService{

	@Resource
	private IEasyBuyUserDao buyUserDao;
	
	/**
	 * 登录的服务
	 */
	@Override
	public EasyBuyUser login(EasyBuyUser easyBuyUser) {
		return buyUserDao.login(easyBuyUser);
	}
	@Override
	public EasyBuyUser saveUser(EasyBuyUser easyBuyUser) {
		if(buyUserDao.saveUser(easyBuyUser)>0){
			return login(easyBuyUser);
		}
		return null;
	}
	@Override
	public EasyBuyUser updateUser(EasyBuyUser easyBuyUser) {
		if(buyUserDao.updateUser(easyBuyUser)>0){
			return login(easyBuyUser);
		}
		return null;
	}
	@Override
	public EasyBuyUser getByName(String userName) {
		return buyUserDao.getByName(userName);
	}
	public IEasyBuyUserDao getBuyUserDao() {
		return buyUserDao;
	}
	public void setBuyUserDao(IEasyBuyUserDao buyUserDao) {
		this.buyUserDao = buyUserDao;
	}
}
