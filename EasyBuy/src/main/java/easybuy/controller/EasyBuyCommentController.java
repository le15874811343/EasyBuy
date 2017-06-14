package easybuy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import easybuy.pojo.EasyBuyComment;
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
  
  /**
   * 进入留言页面
   * @param request
   * @return
   */
  @RequestMapping(value="view.html")
  public String goCommentView(HttpServletRequest request){
	  // 创建目标页
	  int targetPage =1;
	  // 获取页面传递页数
	  String page = request.getParameter("targetPage");
	  // 页数逻辑处理
	  if(null != page && !"".equals(page.trim())){
		  targetPage = Integer.parseInt(page);
	  }
	  // 设置每页显示数
	  int pageSize = 4;
	  // 获取最新的40条留言
	  List < EasyBuyComment> comments = easyBuyCommentService.listByDate(41);
	  // 存入最大记录条数,集合长度
	  request.setAttribute("maxCount", comments.size());
	  // 获取最大记录条数
	  Long maxRowsCount = (long) comments.size();
	  // 分页工具
	  PageUtil pageUtil = new PageUtil(pageSize, maxRowsCount);
	  // 设置最大获取记录的下标
	  int maxNum = targetPage*pageSize>comments.size() ? comments.size():targetPage*pageSize;
	  // 截取集合
	  comments = comments.subList((targetPage-1)*pageSize, maxNum);
	  // 存入留言集合
	  request.setAttribute("comments", comments);
	  // 存入目标页
	  request.setAttribute("targetPage", targetPage);
	  // 存入最大页
	  request.setAttribute("maxPage", pageUtil.getMaxPage());
	  // 存入目前的留言数，用于检测新留言
	  request.setAttribute("oldCount",easyBuyCommentService.count());
	  return "login/guestbook";
  }
  
  /**
   * 保存留言
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value="save.html")
  public void saveComment(HttpServletRequest request,HttpServletResponse response) throws Exception{
	  response.setContentType("text/html;charset=utf-8");
	  // 创建留言对象
	  EasyBuyComment buyComment = new EasyBuyComment();
	  // 获取留言内容
	  String guestContent = request.getParameter("guestContent");
	  // 非空判断
	  if( guestContent == null || guestContent.trim().equals("")){
		  response.getWriter().print("0");
	  } else {
		  // 获取当前登录用户
		 EasyBuyUser easyBuyUser = (EasyBuyUser) request.getSession().getAttribute("user");
		  // 设置创建人昵称
		  buyComment.setEcNickTime(easyBuyUser.getEuUserName());
		  // 设置内容
		  buyComment.setEcContent(guestContent);
		  // 时间格式化
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  // 设创建置时间
		  buyComment.setEcCreateTime(sdf.format(new Date()));
		  // 存入数据库
		  if(easyBuyCommentService.saveComment(buyComment)>0){
			  response.getWriter().print("1");
		  } else{
			  response.getWriter().print("3");
		  }
	  }
	     response.getWriter().flush();//清空缓存,刷新
		 response.getWriter().close();
  }
  
  /**
   * 刷新留言
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping(value="refresh.html")
  public void refresh(HttpServletRequest request,HttpServletResponse response) throws Exception{
	  response.setContentType("text/html;charset=utf-8");
	  // 获取旧的记录条数
	  String oldCount = request.getParameter("oldCount");
	  // 非空判断
	  if(oldCount == null || oldCount.trim().equals("")){
		  response.getWriter().print("1");
	  } else {
		  // 获取最新的数据库留言纪录条数
		  long count = easyBuyCommentService.count();
		  // 判断是否相等，不等则刷新页面
		  if (count != Long.parseLong(oldCount)){
			  response.getWriter().print("1");
		  } else {
			  response.getWriter().print("0");
		  }
	  }
	  response.getWriter().flush();//清空缓存,刷新
		 response.getWriter().close();
  }
public IEasyBuyCommentService getEasyBuyCommentService() {
	return easyBuyCommentService;
}

public void setEasyBuyCommentService(
		IEasyBuyCommentService easyBuyCommentService) {
	this.easyBuyCommentService = easyBuyCommentService;
}
  
  
}
