package kr.co.myservice.restfulapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "RestFul API by Spring Boot 3.x 명세서"
                , description = "Spring Boot 기반의 RestFul API에 대한 명세서 입니다."
                , version = "1.0.0"
        )
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenApi() {
        // swagger에 공개 하고 싶은 api만 설정
        String[] path = {"/users/**" , "/admin/**"};

        return GroupedOpenApi.builder().group("일반 사용자와 관리자를 위한 USER 도메에 대한 API")
                .pathsToMatch(path).build();
    }
}
