package easybuy.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import easybuy.pojo.EasyBuyNews;
import easybuy.service.IEasyBuyNewsService;
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
	
	/**
	 * 进入新闻公告详情页面
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping("/view")
	public String goView(HttpServletRequest request,String url){
		// 新闻Id
		String enId = request.getParameter("enId");
		if(null == enId || "".equals(enId.trim())){
			return "redirect:"+url;
		}
		// 公告
    	Map<Long, EasyBuyNews> actives = actives();
    	// 新闻
    	Map<Long, EasyBuyNews> news = news();
    	// 获取新闻、公告详情
		EasyBuyNews newsDea = easyBuyNewsService.listByEnId(Long.parseLong(enId.trim()));
		request.setAttribute("newsDea", newsDea);
		request.setAttribute("news", news);
    	request.setAttribute("actives", actives);
		return "news-view";
	}

	public IEasyBuyNewsService getEasyBuyNewsService() {
		return easyBuyNewsService;
	}

	public void setEasyBuyNewsService(IEasyBuyNewsService easyBuyNewsService) {
		this.easyBuyNewsService = easyBuyNewsService;
	}
	
	/**
	 * 新闻
	 * @return
	 */
	private Map<Long, EasyBuyNews> news(){
		// 获取最新的前7条新闻
		return easyBuyNewsService.listByDate(8, 1);
	}
	
	/**
	 * 公告
	 * @return
	 */
	private Map<Long, EasyBuyNews> actives(){
		// 获取最新的前7条动态
	   return easyBuyNewsService.listByDate(8, 0);
	}
}
