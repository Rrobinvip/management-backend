package com.rrobinvip.config;

import com.rrobinvip.interceptor.JwtTokenAdminInterceptor;
import com.rrobinvip.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
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

    /**
     * Set up serialize and describable for JSON - Java object. To fix datetime incorrect. Ex. fix the datetime shown like "202403111435" to "2024-03-11 14:35".
     * Current function has conflict with swagger.
     *
     * @param converters the list of configured converters to be extended
     */
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("Extending message converter..");
        // Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Object converter - java obj to json obj
        converter.setObjectMapper(new JacksonObjectMapper());

        converters.addFirst(converter);

        // To fix Swagger not functioning after deploying Jackson message converter. There is a conflict between swagger and jackson converter. Uncommon the following lines to fix the issue.
//        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
//        messageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
//        converters.add(messageConverter);
    }
}