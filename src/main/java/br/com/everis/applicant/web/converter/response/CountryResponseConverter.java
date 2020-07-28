package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Country;
import br.com.everis.applicant.web.dto.response.CountryResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryResponseConverter implements Converter<Country, CountryResponse> {

    @Override
    public CountryResponse convert(Country country) {
        return CountryResponse.builder()
                .id(country.getId())
                .description(country.getDescription()).build();
    }
}