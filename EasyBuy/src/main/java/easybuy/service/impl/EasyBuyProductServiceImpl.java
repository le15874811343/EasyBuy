package easybuy.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import easybuy.dao.IEasyBuyProductDao;
import easybuy.pojo.EasyBuyProduct;
import easybuy.service.IEasyBuyProductService;

@Service
public class EasyBuyProductServiceImpl implements IEasyBuyProductService{
    @Resource
	private IEasyBuyProductDao productDao;
    
    
    /**
	 * 根据折扣率升序获取商品集合
	 * @param maxRowsNumber 获取最大记录数
	 * @return
	 */
	@Override
	public Map<Long, EasyBuyProduct> listByRebate(int maxRowsNumber) {
		return productDao.listByRebate(maxRowsNumber);
	}

	/**
	 * 根据销售额降序获取商品集合
     * @param maxRowsNumber 获取最大记录数
	 * @return
	 */
	@Override
	public Map<Long, EasyBuyProduct> listBySaleCount(int maxRowsNumber) {
		return productDao.listBySaleCount(maxRowsNumber);
	}
	
	/**
	 * 根据商品编号获取商品详情
	 * @param epId 商品编号
	 * @return 
	 */
	@Override
	public EasyBuyProduct getById(Long epId) {
		return productDao.getById(epId);
	}
	
	/**
	  * 根据商品二级分类获取商品详情
	  * @param epcChildId 二级分类编号
	  * @param targetPage 当前页
	  * @param pageSize 每页最大数
	  * @return
	  */
	@Override
	public Map<Long, EasyBuyProduct> listByChildId(Double epRebate,Long epcId,Long epcChildId,int targetPage, int pageSize) {
		return productDao.listByChildId(epRebate,epcId,epcChildId, targetPage, pageSize);
	}

	/**
	 * 根据商品二级分类获取商品数量
	 * @param epcChildId 二级分类编号
	 * @return
	 */
	@Override
	public Long countByChildId(Double epRebate,Long epcId,Long epcChildId) {
		return productDao.countByChildId(epRebate,epcId,epcChildId);
	}
	
	@Override
	public int upDate(EasyBuyProduct easyBuyProduct) {
		return productDao.upDate(easyBuyProduct);
	}
	
	public IEasyBuyProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(IEasyBuyProductDao productDao) {
		this.productDao = productDao;
	}






	
	
}
