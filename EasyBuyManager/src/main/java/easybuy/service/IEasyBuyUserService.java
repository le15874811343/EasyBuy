package easybuy.service;

import java.util.List;

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
	
	public  List<EasyBuyUser> list(EasyBuyUser easyBuyUser,int targetPage,int pageSize);
	
	public long count(EasyBuyUser easyBuyUser);
	
	public EasyBuyUser getById(String userId);
	
	public int delete(String userId);
}
