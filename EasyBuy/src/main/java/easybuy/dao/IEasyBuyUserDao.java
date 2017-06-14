package easybuy.dao;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyUser;

@Repository
public interface IEasyBuyUserDao {
  /**
   * 登录的方法
   * @param easyBuyUser
   * @return
   */
  EasyBuyUser login(EasyBuyUser easyBuyUser);
  
  EasyBuyUser getByName(String userName);
  
  int saveUser(EasyBuyUser easyBuyUser);
  
  int updateUser(EasyBuyUser easyBuyUser);

}
