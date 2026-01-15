package com.aloha.product_rest.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
 
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                // 그룹명 설정
                .group("aloha") // 그룹명 설정
                // API 명세서가 적용될 경로 설정
                // .pathsToMatch("/**")
                .pathsToMatch("/api/**", "/products/**")
                // 경로 제외
                // ** -> 모든 경로 적용
                .pathsToExclude("/admin/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Test Proejct API")
                        .description("Test 프로젝트 API 입니다.")
                        .version("v0.0.1")
                        .contact(new Contact()
                                    .name("ALOHA")
                                    .email("wwwaloha@xxxxx.com")
                                )
                    );
    }

    
}
