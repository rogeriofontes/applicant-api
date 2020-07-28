package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Country;
import br.com.everis.applicant.web.dto.request.CountryRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryRequestConverter implements Converter<CountryRequest, Country> {

    @Override
    public Country convert(CountryRequest request) {
        return Country.builder()
                .description(request.getDescription()).build();
    }
}