package easybuy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyOrder;
import easybuy.pojo.vo.EasyBuyOrderVo;

@Repository
public interface IEasyBuyOrderDao {
   int saveOrder(EasyBuyOrder easyBuyOrder);
   
   List< EasyBuyOrderVo> listVo(String status,Long eoId,Long userId,int targetPage,int pageSize);
   
   long count(String status,Long eoId,Long userId);
   
   long countAll();
   
   int updateStatus(EasyBuyOrder easyBuyOrder);
}
