package com.rrobinvip.config;

import com.rrobinvip.interceptor.JwtTokenAdminInterceptor;
import com.rrobinvip.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@EnableWebMvc
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Start registering custom interceptors...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("Extending message converter..");
        // Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Object converter - java obj to json obj
        converter.setObjectMapper(new JacksonObjectMapper());

        converters.addFirst(converter);
    }
}