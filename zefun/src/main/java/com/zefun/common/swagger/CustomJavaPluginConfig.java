package com.zefun.common.swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * ClassName: CustomJavaPluginConfig   
 * date: 2016年3月22日 下午3:39:03  
 * @author michael  
 * @version   
 * @since JDK 1.8
 */
@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages = { "com.zefun.app"})

public class CustomJavaPluginConfig extends WebMvcConfigurerAdapter {
	/**
	 * 注入SpringSwaggerConfig
	 */
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * 链式编程 来定制API样式 后续会加上分组信息
	 * 
	 * @return bean
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo()).includePatterns(".*")
				.useDefaultResponseMessages(false)
//				.pathProvider(new GtPaths())
				.apiVersion("0.1").swaggerGroup("user");

	}
    /**
     * 
     * apiInfo:(描述api基本信息).   
     * @author maywant  
     * @return  apiInfo
     * @since JDK 1.8
     */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("maywant后台API接口平台",
				"提供详细APP后台所有restful接口", "com",
				"http://www.maywant.com/zefun/", "maywant 官网", "http://www.maywant.com/zefun/");
		return apiInfo;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
    /**
     * 
     * ClassName: GtPaths   
     * date: 2016年3月22日 下午3:39:46  
     * @author maywant  
     * @version CustomJavaPluginConfig  
     * @since JDK 1.8
     */
	class GtPaths extends SwaggerPathProvider {

		@Override
		protected String applicationPath() {
			return "/restapi";
		}

		@Override
		protected String getDocumentationPath() {
			return "/restapi";
		}
	}
}
