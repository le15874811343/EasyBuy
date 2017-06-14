package easybuy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public  List<EasyBuyUser> list(EasyBuyUser easyBuyUser,int targetPage,int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != easyBuyUser.getEuUserName()){
			params.put("euUserName",easyBuyUser.getEuUserName());
		}
		if(null != easyBuyUser.getEuSex()){
			params.put("euSex",easyBuyUser.getEuSex());
		}
		if(null != easyBuyUser.getEuBirthdayl()){
			params.put("euBirthdayl",easyBuyUser.getEuBirthdayl());
		}
		if(null != easyBuyUser.getEuIdentityCode()){
			params.put("euIdentityCode",easyBuyUser.getEuIdentityCode());
		}
		if(null != easyBuyUser.getEuEmail()){
			params.put("euEmail",easyBuyUser.getEuEmail());
		}
		if(null != easyBuyUser.getEuMobile()){
			params.put("euMobile",easyBuyUser.getEuMobile());
		}
		if(null != easyBuyUser.getEuAddress()){
			params.put("euAddress",easyBuyUser.getEuAddress());
		}
		if(null != easyBuyUser.getEuStatus()){
			params.put("euStatus",easyBuyUser.getEuStatus());
		}
		params.put("max", targetPage*pageSize+1);
		params.put("min", (targetPage-1)*pageSize);
		return super.getSqlSessionTemplate().selectList("cn.com.pojo.mapping.EasyBuyUserMapper.list", params);
	}

	@Override
	public long count(EasyBuyUser easyBuyUser) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != easyBuyUser.getEuUserName()){
			params.put("euUserName",easyBuyUser.getEuUserName());
		}
		if(null != easyBuyUser.getEuSex()){
			params.put("euSex",easyBuyUser.getEuSex());
		}
		if(null != easyBuyUser.getEuBirthdayl()){
			params.put("euBirthdayl",easyBuyUser.getEuBirthdayl());
		}
		if(null != easyBuyUser.getEuIdentityCode()){
			params.put("euIdentityCode",easyBuyUser.getEuIdentityCode());
		}
		if(null != easyBuyUser.getEuEmail()){
			params.put("euEmail",easyBuyUser.getEuEmail());
		}
		if(null != easyBuyUser.getEuMobile()){
			params.put("euMobile",easyBuyUser.getEuMobile());
		}
		if(null != easyBuyUser.getEuAddress()){
			params.put("euAddress",easyBuyUser.getEuAddress());
		}
		if(null != easyBuyUser.getEuStatus()){
			params.put("euStatus",easyBuyUser.getEuStatus());
		}
		return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyUserMapper.count", params);
	}

	@Override
	public EasyBuyUser getById(String userId) {
	return super.getSqlSessionTemplate().selectOne("cn.com.pojo.mapping.EasyBuyUserMapper.getById",  userId);
	}

	@Override
	public int delete(String userId) {
		return super.getSqlSessionTemplate().delete("cn.com.pojo.mapping.EasyBuyUserMapper.delete", userId);
	}

}
