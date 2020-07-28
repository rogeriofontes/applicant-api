package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.web.dto.request.ProgrammingLanguageRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProgrammingLanguageRequestConverter implements Converter<ProgrammingLanguageRequest, ProgrammingLanguage> {

    @Override
    public ProgrammingLanguage convert(ProgrammingLanguageRequest request) {
        return ProgrammingLanguage.builder()
                .description(request.getDescription())
                .build();
    }
}