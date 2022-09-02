package com.jaksim3.bak.conifg;

import com.jaksim3.bak.web.controller.AuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableWebMvc 안되면 넣고 빼고 해봐
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html") // "/swagger-ui/index.html"
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.jaksim3.bak.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
//              .tags(new Tag("AuthController", "큰 제목 추가") );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("금융상품 추천 서비스")
                .description("금융상품 추천 API입니다.")
                .version("1.0.1")
                .license("LICENSE")
                .build();
    }
}