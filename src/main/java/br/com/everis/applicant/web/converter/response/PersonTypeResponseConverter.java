package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.PersonType;
import br.com.everis.applicant.web.dto.response.PersonTypeResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonTypeResponseConverter implements Converter<PersonType, PersonTypeResponse> {
    @Override
    public PersonTypeResponse convert(PersonType source) {
        return PersonTypeResponse.builder()
                .id(Long.valueOf(source.getOrdinal()))
                .name(source.getName())
                .code(source.name())
                .build();
    }
}
