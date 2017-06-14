package easybuy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import easybuy.pojo.EasyBuyProduct;

/**
 * 商品详细信息处理接口
 * @author 唐华
 *
 */
@Repository
public interface IEasyBuyProductDao {
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
	  * 根据商品分类获取商品详情
	  * @param epcChildId 二级分类编号
	  * @param epcId 一级分类编号
	  * @param targetPage 当前页
	  * @param pageSize 每页最大数
	  * @return
	  */
	 List<EasyBuyProduct> listByChildId(Double epRebate, Long epcId,Long epcChildId,int targetPage,int pageSize);
	 
	 /**
	  * 根据商品二级分类获取商品数量
	  * @param epcChildId 二级分类编号
	  * @param epcId 一级分类编号
	  * @return
	  */
	 Long countByChildId(Double epRebate,Long epcId,Long epcChildId);
	 
	 int update(EasyBuyProduct easyBuyProduct);
	 
	 int save(EasyBuyProduct easyBuyProduct);
	 
	 int delete(Long epId);
}
