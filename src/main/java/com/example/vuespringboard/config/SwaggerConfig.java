package com.example.vuespringboard.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * class       :SwaggerConfig
 * Package      :com.example.vuespringboard.config
 *
 * @Description:
 * @author: SR45
 * @since: 2024-08-07 오후 3:08
 * 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2024-08-07 :            : 신규 개발.
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger-config.title}")
    private String title;

    @Value("${swagger-config.prefix}")
    private String prefix;

    @Value("${swagger-config.url}")
    private String url;

    @Bean
    public OpenAPI springShopOpenAPI() {

        Info info = new Info()
                .version("v0.1")
                .title(title)
                .description("VueSpringBoard 프로젝트 API 문서");

        Server server = new Server();

        server.setUrl(url + prefix);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }

}
