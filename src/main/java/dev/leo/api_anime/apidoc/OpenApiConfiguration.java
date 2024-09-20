package dev.leo.api_anime.apidoc;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.annotations.OpenAPI31;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPI31
public class OpenApiConfiguration {

    private Contact getContact(){
        return new Contact().name("Leonardo Henrique Silva").email("leonardosilva1360@gmail.com");
    }

    private Info getApiInfos(){
        return new Info().contact(getContact()).title("Anime Api").description("Api para consulta e cadastro de Anime com suas temporadas e epsodios");
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Anime Api")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(api -> api.info(getApiInfos()))
                .build();
    }

}
