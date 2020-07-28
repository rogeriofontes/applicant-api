package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Country;
import br.com.everis.applicant.model.repository.CountryRepository;
import br.com.everis.applicant.model.service.CountryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, Country country) {
        country.update(id, country);
        return countryRepository.save(country);
    }

    @Override
    @Cacheable(Constants.COUNTRYS_IN_CACHE)
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    @Cacheable(Constants.COUNTRYS_IN_CACHE)
    public Page<Country> findAllPageable(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.COUNTRYS_IN_CACHE)
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            countryRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
