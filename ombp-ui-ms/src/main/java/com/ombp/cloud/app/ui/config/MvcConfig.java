package com.ombp.cloud.app.ui.config;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Value("${file.upload.path}")
	private String fileUploadPath;
	
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/home").setViewName("home");
		//registry.addViewController("/").setViewName("pages/public");
		registry.addViewController("/app").setViewName("pages/index");
		//registry.addViewController("/info/quiz").setViewName("/pages/quiz");
		//registry.addViewController("/info/articles").setViewName("/pages/articles");
		//registry.addViewController("/login").setViewName("pages/login");
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String publicFilesDir = String.format("file:%s/"+fileUploadPath+"/", System.getProperty("user.dir"));
		
		String scansDir = String.format("file:%s/scans/", System.getProperty("user.dir"));
		
		String viewerJS = String.format("file:%s/"+"/ViewerJS/"+"/", System.getProperty("user.dir"));
		
		 VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
			        .addVersionStrategy(new ContentVersionStrategy(), "/**");

        registry.addResourceHandler("/"+fileUploadPath+"/**")
                .addResourceLocations(publicFilesDir);
        
        registry.addResourceHandler("/scans/**")
        .addResourceLocations(scansDir);
        
        registry.addResourceHandler("/ViewerJS/**")
        .addResourceLocations(viewerJS);
        
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        
        registry
        .addResourceHandler("/resources/sw.js")
        .addResourceLocations("/sw.js");
        
        registry.addResourceHandler("/*/**")
        .addResourceLocations("classpath:/static/")
        .setCachePeriod(60 * 60 * 24 * 365) /* one year */
        .resourceChain(true)
        .addResolver(versionResourceResolver);
        
    }
	
	@Bean
    public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    
}