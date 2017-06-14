package easybuy.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




/**
 * 验证码处理控制器
 * 
 * @author 唐华
 *
 */
@Controller
@RequestMapping("/identifyingCode")
public class IdentifyingCodeController {
	static{
        System.setProperty("java.awt.headless", "true");
    }
	
	/**
	 * 加载验证码
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/load.html")
	public String index(HttpServletResponse response,HttpServletRequest request){
		// 宽度
		int width = 60;
		// 高度
		int height = 20;
		// 创建图片缓存流
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 创建画图对象
		Graphics g = buffImg.createGraphics();
		// 随机数对象
		Random random = new Random();
		// 设置颜色
		g.setColor(Color.WHITE);
		// 设置输出图片大小
		g.fillRect(0, 0, width, height);
		// 字体
		Font font = new Font("Times New Roman", Font.PLAIN, 18);
		// 设置字体
		g.setFont(font);
		// 设置颜色
		g.setColor(Color.GRAY);
		// 设置文件大小
		g.fillRect(0, 0, width - 1, height - 1);
		// 设置颜色
		g.setColor(Color.pink);
		// 获取小于200的随机整数
		for (int i = 0; i < 200; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			// 线性输出到图片
			g.drawLine(x, y, x1, y1);
		}
		// 缓存流
		StringBuffer randomCode = new StringBuffer();
		// 颜色
		int red = 0, green = 0, blue = 0;
		// 根据随机数获取四个随机字符
		for (int i = 0; i < 4; i++) {
			// dobule 转char
			char rand = (char) (Math.random() * 26 + 'A');
			// 红色程度
			red = random.nextInt(110);
			// 绿色程度
			green = random.nextInt(90);
			// 蓝色程度
			blue = random.nextInt(50);
			// 设置颜色
			g.setColor(new Color(red, green, blue));
			// 输出文字
			g.drawString(Character.toString(rand), 13 * i + 6, 16);
			// 存入生成的验证码字符
			randomCode.append(rand);
		}
		// 设置验证码字符到Session
		request.getSession().setAttribute("sRand", randomCode.toString());
		// 销毁画图对象
		g.dispose();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 生成图片
		response.setContentType("image/jpeg");
		try {
			ImageIO.write(buffImg, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
