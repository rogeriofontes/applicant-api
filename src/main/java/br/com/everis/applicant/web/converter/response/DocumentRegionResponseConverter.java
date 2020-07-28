package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.DocumentRegion;
import br.com.everis.applicant.web.dto.response.DocumentRegionResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentRegionResponseConverter implements Converter<DocumentRegion, DocumentRegionResponse> {
    @Override
    public DocumentRegionResponse convert(DocumentRegion source) {
        return DocumentRegionResponse.builder()
                .name(source.getState())
                .code(source.name())
                .acronym(source.getAcronym())
                .build();
    }
}
