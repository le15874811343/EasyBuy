package easybuy.dao;

import java.util.List;
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
  
  List<EasyBuyUser> list(EasyBuyUser easyBuyUser,int targetPage,int pageSize);
  
  long count(EasyBuyUser easyBuyUser);
  
  EasyBuyUser getById(String userId);
  
  int delete (String userId);
}
