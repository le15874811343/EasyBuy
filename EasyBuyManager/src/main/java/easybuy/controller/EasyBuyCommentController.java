package easybuy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;

import easybuy.pojo.EasyBuyComment;
import easybuy.pojo.EasyBuyNews;
import easybuy.pojo.EasyBuyUser;
import easybuy.service.IEasyBuyCommentService;
import easybuy.util.PageUtil;
/**
 * 留言处理controller
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/comment")
public class EasyBuyCommentController {
  @Resource
  private IEasyBuyCommentService easyBuyCommentService;
  
  @RequestMapping(value="/list")
  @ResponseBody
  public Map<String, Object> list(HttpServletRequest request){
	  Map<String, Object> results = new HashMap<String, Object>();
	  String  ecReply = request.getParameter("ecReply");
	  EasyBuyComment easyBuyComment = new EasyBuyComment();
	  if(null!=ecReply && !ecReply.trim().equals("")){
		  easyBuyComment.setEcReply(ecReply);
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
  	long maxRowsCount = easyBuyCommentService.realCount(easyBuyComment);
  	PageUtil pageUtil = new PageUtil(pageSize, maxRowsCount);
  	List<EasyBuyComment> comments = easyBuyCommentService.list(easyBuyComment,targetPage, pageSize);
  	results.put("comments", comments);
  	results.put("targetPage", targetPage);
  	results.put("pageSize", pageSize);
  	results.put("maxCount", maxRowsCount);
  	results.put("maxPage",pageUtil.getMaxPage());
  	return results;
  }
  
  @RequestMapping(value="/replyView")
  @ResponseBody
  public EasyBuyComment replyView(HttpServletRequest request){
	  String ecId = request.getParameter("ecId");
	  return easyBuyCommentService.getById(Long.parseLong(ecId));
  }
 
  @RequestMapping(value="/reply")
  @ResponseBody
  public int reply(HttpServletRequest request){
	 String ecId = request.getParameter("ecId");
     String ecReply = request.getParameter("ecReply");
     EasyBuyComment updateComment = new EasyBuyComment();
     updateComment.setEcId(Long.parseLong(ecId));
     updateComment.setEcReply(ecReply);
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     updateComment.setEcReplyTime(sdf.format(new Date()));
     EasyBuyComment Comment = new EasyBuyComment();
     EasyBuyUser easyBuyUser =  (EasyBuyUser) request.getSession().getAttribute("user");
     Comment.setEcNickTime(easyBuyUser.getEuUserName());
     Comment.setEcContent("有新回复屏蔽");
     Comment.setEcContent("有新回复屏蔽");
     Comment.setEcCreateTime(sdf.format(new Date()));
     if(easyBuyCommentService.saveComment(Comment)>0&&easyBuyCommentService.update(updateComment)>0){
    	 return 1;
     }
     return 0;
  }
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public int delete(HttpServletRequest request){
     String ecId = request.getParameter("ecId");
     if(easyBuyCommentService.delete(Long.parseLong(ecId))>0){
    	 return 1;
     }
     return 0;
  }
public IEasyBuyCommentService getEasyBuyCommentService() {
	return easyBuyCommentService;
}

public void setEasyBuyCommentService(
		IEasyBuyCommentService easyBuyCommentService) {
	this.easyBuyCommentService = easyBuyCommentService;
}
  
  
}
