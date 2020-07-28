package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.web.dto.response.ProgrammingLanguageResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProgrammingLanguageResponseConverter implements Converter<ProgrammingLanguage, ProgrammingLanguageResponse> {

    @Override
    public ProgrammingLanguageResponse convert(ProgrammingLanguage programmingLanguage) {
        return ProgrammingLanguageResponse.builder()
                .id(programmingLanguage.getId())
                .description(programmingLanguage.getDescription())
                .build();
    }
}