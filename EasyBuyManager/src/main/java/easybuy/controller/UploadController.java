package easybuy.controller;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class UploadController {
 @RequestMapping("/upload")
	  public void doPost(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		  
		  
		  bancfsc(request,session);
		  String filepath =session.getServletContext().getRealPath("/tepimages")+"/";
	      String filename = "";  
	      String type="";  
	      ServletInputStream in = request.getInputStream(); 
	      
	      byte[] buf = new byte[4048];  
	      int len = in.readLine(buf, 0, buf.length);  
	      String f = new String(buf, 0, len - 1);   
	      while ((len = in.readLine(buf, 0, buf.length)) != -1) {  
	          filename = new String(buf, 0, len);  
	          int j = filename.lastIndexOf("\"");  
	          int p = filename.lastIndexOf(".");   
	          //�ļ�����  
	          type=filename.substring(p,j);     
	          //�ļ����  
	          filename = System.currentTimeMillis()+type;    
	          DataOutputStream fileStream = new DataOutputStream(  
	          new BufferedOutputStream(new FileOutputStream(filepath+ filename))  
	          );  
	          DataOutputStream stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filepath.replace("Manager", "")+ filename)));
	          len = in.readLine(buf, 0, buf.length);   
	          len = in.readLine(buf, 0, buf.length);   
	          while ((len = in.readLine(buf, 0, buf.length)) != -1) {  
	              String tempf = new String(buf, 0, len - 1);  
	              if (tempf.equals(f) || tempf.equals(f + "--")) {  
	                  break;      
	              }  
	              else{  
	               // д��  
	                   fileStream.write(buf, 0, len); 
	                   stream.write(buf,0,len);
	              }  
	          }  
	          fileStream.close();
	          stream.close();
	        session.setAttribute("sctname", filename);
	      }  
	      PrintWriter out=response.getWriter();  
	      String result = filename ;  
	      out.print(result);  
	      out.close();  
	      in.close();  
	  }
	  private void bancfsc(HttpServletRequest request,HttpSession session){
		  String	sctname=(String)  request.getParameter("sctname");
		 if(sctname!=null&&!sctname.equals(""))  {
			String url=session.getServletContext().getRealPath("/tepimages")+"/"+sctname;
		    File file=new File(url);
		    if(file.exists()){
		    	file.delete();
		    }
		   }
	  }
}
