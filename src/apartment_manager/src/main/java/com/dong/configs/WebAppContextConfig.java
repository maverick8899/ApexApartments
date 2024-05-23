package com.dong.configs;

import java.text.SimpleDateFormat;

import com.dong.formatters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dong.controllers",
    "com.dong.repository",
    "com.dong.service"
})
@PropertySource("classpath:configs.properties")
public class WebAppContextConfig implements WebMvcConfigurer {


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addFormatter(new RoomFormatter());
        registry.addFormatter(new AccountsFormatter());
        registry.addFormatter(new ServiceFormat());
        registry.addFormatter(new MerchandiseFormatter());


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");

    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
