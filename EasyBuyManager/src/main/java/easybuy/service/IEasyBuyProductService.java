package easybuy.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyProduct;

@Repository
public interface IEasyBuyProductService {
	/**
	 * 根据折扣率升序获取商品集合
	 * @param maxRowsNumber 获取最大记录数
	 * @return
	 */
	 Map<Long, EasyBuyProduct> listByRebate(int maxRowsNumber);
	 /**
	  * 根据销售额降序获取商品集合
      * @param maxRowsNumber 获取最大记录数
	  * @return
	  */
	 Map<Long, EasyBuyProduct> listBySaleCount(int maxRowsNumber);
	 
	 /**
	  * 根据商品编号获取商品详情
	  * @param epId
	  * @return
	  */
	 EasyBuyProduct getById(Long epId);
	 
	 /**
	  * 根据商品二级分类获取商品详情
	  * @param epcChildId 二级分类编号
	  * @param targetPage 当前页
	  * @param pageSize 每页最大数
	  * @return
	  */
	 List<EasyBuyProduct> listByChildId(Double epRebate,Long epcId ,Long epcChildId,int targetPage, int pageSize);
	
	/**
	 * 根据商品二级分类获取商品数量
	 * @param epcChildId 二级分类编号
	 * @return
	 */
	public Long countByChildId(Double epRebate,Long epcId ,Long epcChildId) ;
	
	public int update(EasyBuyProduct easyBuyProduct);
	
	public int save(EasyBuyProduct easyBuyProduct);
	
	public int delete(Long epId);
}
