package easybuy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.pojo.EasyBuyNews;
import easybuy.pojo.ProductCategory;
import easybuy.service.IEasyBuyNewsService;
import easybuy.util.PageUtil;
/**
 * 新闻处理controller
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/news")
public class EasyBuyNewsController {

	@Resource
	private IEasyBuyNewsService easyBuyNewsService;
	

	
	@RequestMapping("/modifyView")
	@ResponseBody
	public EasyBuyNews modifyView(HttpServletRequest request){
		String enId = request.getParameter("enId");
		return easyBuyNewsService.listByEnId(Long.parseLong(enId));
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request){
		Map<String, Object> results = new HashMap<String, Object>();
	    String enType =	request.getParameter("enType");
	    EasyBuyNews easyBuyNews = new EasyBuyNews();
	    if(null != enType && !enType.trim().equals("")){
	    	easyBuyNews.setEnType(Integer.parseInt(enType));
	    }
	    // 获取页面传递的目标页
    	String page = request.getParameter("targetPage");
    	// 设置目标页
    	int targetPage =1;
    	// 页码逻辑处理
    	if(null!=page&&!"".equals(page)){
    		targetPage = Integer.parseInt(page);
    	}
    	int pageSize = 8;
    	long maxRowsCount = easyBuyNewsService.count(easyBuyNews);
    	PageUtil pageUtil = new PageUtil(pageSize, maxRowsCount);
    	List<EasyBuyNews> news = easyBuyNewsService.list(easyBuyNews,targetPage, pageSize);
    	results.put("news", news);
    	results.put("targetPage", targetPage);
    	results.put("pageSize", pageSize);
    	results.put("maxCount", maxRowsCount);
    	results.put("maxPage",pageUtil.getMaxPage());
		return results;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public int save(HttpServletRequest request){
	 String enType =	request.getParameter("enType");
	 String enTitle =	request.getParameter("enTitle");
	 String enContent =	request.getParameter("enContent");
	 EasyBuyNews easyBuyNews = new EasyBuyNews();
	 easyBuyNews.setEnContent(enContent);
	 easyBuyNews.setEnTitle(enTitle);
	 easyBuyNews.setEnType(Integer.parseInt(enType));
	// 时间格式化
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	// 设置创建时间
 	easyBuyNews.setEnCreateTime(sdf.format(new Date()));
	 if(easyBuyNewsService.save(easyBuyNews)>0){
		 return 1;
	 }
	 return 0;
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public int update(HttpServletRequest request){
		 String enId = request.getParameter("enId");
		 String enType =	request.getParameter("enType");
		 String enTitle =	request.getParameter("enTitle");
		 String enContent =	request.getParameter("enContent");
		 EasyBuyNews easyBuyNews = new EasyBuyNews();
		 easyBuyNews.setEnContent(enContent);
		 easyBuyNews.setEnTitle(enTitle);
		 easyBuyNews.setEnType(Integer.parseInt(enType));
		 easyBuyNews.setEnId(Long.parseLong(enId));
		// 时间格式化
	 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	// 设置创建时间
	 	easyBuyNews.setEnCreateTime(sdf.format(new Date()));
		 if(easyBuyNewsService.update(easyBuyNews)>0){
			 return 1;
		 }
		 return 0;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(HttpServletRequest request){
	 String enId = request.getParameter("enId");
	if(easyBuyNewsService.delete(Long.parseLong(enId))>0){
		return 1;
	}
	return 0;
	}
	
	public IEasyBuyNewsService getEasyBuyNewsService() {
		return easyBuyNewsService;
	}

	public void setEasyBuyNewsService(IEasyBuyNewsService easyBuyNewsService) {
		this.easyBuyNewsService = easyBuyNewsService;
	}
	

}
