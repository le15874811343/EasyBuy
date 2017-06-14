package easybuy.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"easybuy.config","easybuy.dao","easybuy.dao.impl","easybuy.service","easybuy.service.impl","easybuy.controller"})
public class RootConfig extends WebMvcConfigurerAdapter{
/**
 * 视图解析组件
 * @return
 */
@Bean
public ViewResolver viewResolver(){
	InternalResourceViewResolver resolver=new InternalResourceViewResolver();
	resolver.setPrefix("/");
	resolver.setSuffix(".jsp");
	resolver.setExposeContextBeansAsAttributes(true);
	resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
	return resolver;
}

/**
 * 静态资源可请求配置
 */
@Override
public void configureDefaultServletHandling(
		DefaultServletHandlerConfigurer configurer) {
          configurer.enable();
}

}
