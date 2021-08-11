package id.muhtadien.apijamaah.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                //.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                //.apis(RequestHandlerSelectors.basePackage("id.muhtadien.apijamaah.controllers"))
                //.paths(regex("jamaah.*"))
                //.paths(PathSelectors.ant())
                .build().apiInfo(apiInfo());
    }

    /*private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("JavaInUse API")
                .description("JavaInUse API reference for developers")
                .termsOfServiceUrl("http://javainuse.com")
                .contact(new Contact("Hangga Aji Sayekti", "hangga.web.id", "bazeniancode@gmail.com"))
                .license("JavaInUse License")
                .licenseUrl("javainuse@gmail.com").version("1.0").build();
    }
}
