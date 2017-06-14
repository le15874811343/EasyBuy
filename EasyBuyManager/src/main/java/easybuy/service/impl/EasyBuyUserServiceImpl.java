package easybuy.service.impl;

import java.util.List;

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
	
	@Override
	public long count(EasyBuyUser easyBuyUser) {
		return buyUserDao.count(easyBuyUser);
	}
	
	@Override
	public   List<EasyBuyUser> list(EasyBuyUser easyBuyUser,int targetPage,int pageSize) {
		return buyUserDao.list(easyBuyUser,targetPage,pageSize);
	}
	
	@Override
	public EasyBuyUser getById(String userId) {
		return buyUserDao.getById(userId);
	}
	
	@Override
	public int delete(String userId) {
		return buyUserDao.delete(userId);
	}
	public IEasyBuyUserDao getBuyUserDao() {
		return buyUserDao;
	}
	public void setBuyUserDao(IEasyBuyUserDao buyUserDao) {
		this.buyUserDao = buyUserDao;
	}
}
