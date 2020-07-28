package br.com.everis.applicant.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    /**
     * Api info.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API de Serviços Rest Mantenedora do Domínio: Gestão de Home Office")
                .description("\"API criada para expor serviços rest associados ao domínio de gestão de home office\"")
                .termsOfServiceUrl("http://uaijug.com.br").license("Licenciado por Everis")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\\").version("1.0")
                .contact(new Contact("Everis", "https:\\www.everis.com.br", "https:\\\\www.everis.com.br")).build();
    }
}
