package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Center;
import br.com.everis.applicant.model.entity.Country;
import br.com.everis.applicant.model.service.CountryService;
import br.com.everis.applicant.web.dto.request.CenterRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CenterRequestConverter implements Converter<CenterRequest, Center> {

    private CountryService countryService;

    public CenterRequestConverter(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Center convert(CenterRequest request) {
        Optional<Country> country = countryService.findById(request.getCountryId());

        return Center.builder()
                .description(request.getDescription())
                .city(request.getCity())
                .state(request.getState())
                .country(country.isPresent() ? country.get() : new Country()).build();
    }
}