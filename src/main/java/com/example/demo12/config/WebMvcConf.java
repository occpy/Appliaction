package com.example.demo12.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {
    @Value("${pro.uploadPath}")
    private String fileRootPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("File/**") // 添加访问路径
                // file: 当前项目根目录, 映射到真实的路径下的 uploadPath(变量)目录下
                .addResourceLocations("file:" + fileRootPath + "/");
    }
}
