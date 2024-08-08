package com.example.vuespringboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * class       :WebMvcConfig
 * Package      :com.example.vuespringboard.config
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-08 오전 11:53
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-08 :            : 신규 개발.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // cors를 적용할 url 패턴을 정의
//                //.allowedOrigins("*") //자원을 공유를 허락할 Origin을 지정
//                .allowedOriginPatterns("*")
//                /*
//                .allowedOrigins("https://sportsclub-dev.sports.or.kr", "https://sportsclub-api-dev.sports.or.kr",
//                                "http://localhost:8080","http://localhost:9001","http://localhost:9002",
//                                "http://localhost:9003","http://localhost:9004","http://localhost:9005",
//                                "http://localhost:9009") // 허용할 출처
//                 */
//                .exposedHeaders("respCode", "respMsg")
//                .allowedMethods("*") // http method를 지정할
//                .allowedHeaders("*")
//                .allowCredentials(true) // 쿠키 인증 요청 허용
//        ;
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
