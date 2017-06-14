package easybuy.dao.impl;

import org.springframework.stereotype.Repository;

import easybuy.dao.IEasyBuyUserDao;
import easybuy.pojo.EasyBuyUser;

@Repository
public class EasyBuyUserDaoImpl extends BaseDao implements IEasyBuyUserDao{

	/**
	 * 登录的方法
	 */
	@Override
	public EasyBuyUser login(EasyBuyUser easyBuyUser) {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyUserMapper.login", easyBuyUser);
	}

	@Override
	public EasyBuyUser getByName(String userName) {
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyUserMapper.getByName",  userName);
	}

	@Override
	public int saveUser(EasyBuyUser easyBuyUser) {
		return super.getSqlSessionTemplate().insert("cn.com.pojo.mapping.EasyBuyUserMapper.insertSelective", easyBuyUser);
	}

	@Override
	public int updateUser(EasyBuyUser easyBuyUser) {
		return super.getSqlSessionTemplate().update("cn.com.pojo.mapping.EasyBuyUserMapper.update", easyBuyUser);
	}

}
