package org.multi.final_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class FileWebConfig implements WebMvcConfigurer {

    @Value("${file.dir}")
    private String realPath;

    //Thymeleaf 페이지에서 이미지 경로를 인식시켜 주기 위한 설정
    private String connectPath = "/uploadimgPath/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("realPath : {}",realPath);
        registry.addResourceHandler(connectPath).addResourceLocations("file:///"+realPath);
    }
}
