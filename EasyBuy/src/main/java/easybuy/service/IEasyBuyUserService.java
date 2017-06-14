package easybuy.service;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyUser;
@Repository
public interface IEasyBuyUserService {
	/**
	 * 登录的服务
	 * @param easyBuyUser
	 * @return
	 */
	public EasyBuyUser login(EasyBuyUser easyBuyUser);
	
	public EasyBuyUser getByName(String userName);
	
	public EasyBuyUser saveUser(EasyBuyUser easyBuyUser);
	
	public EasyBuyUser updateUser(EasyBuyUser easyBuyUser);
}
