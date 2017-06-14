package easybuy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyOrder;
import easybuy.pojo.vo.EasyBuyOrderVo;

@Repository
public interface IEasyBuyOrderDao {
   int saveOrder(EasyBuyOrder easyBuyOrder);
   
   List< EasyBuyOrderVo> listVo(String status,Long eoId,Long userId,String userName,int targetPage,int pageSize);
   
   long count(String status,Long eoId,Long userId,String userName);
   
   long countAll();
   
   int updateStatus(EasyBuyOrder easyBuyOrder);
   
   int delete(Long eoId);
}
