package easybuy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyOrder;
import easybuy.pojo.vo.EasyBuyOrderVo;

@Repository
public interface IEasyBuyOrderService {
	public int saveOrder(EasyBuyOrder easyBuyOrder) ;
	
	public  List< EasyBuyOrderVo> listVo(String status,Long eoId, Long userId,	int targetPage, int pageSize);
	
	public long count(String status,Long eoId, Long userId);
	
	long countAll();
	
	public int updateStatus(EasyBuyOrder easyBuyOrder);
}
