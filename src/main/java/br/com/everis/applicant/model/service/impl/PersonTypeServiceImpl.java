package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.PersonType;
import br.com.everis.applicant.model.service.PersonTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonTypeServiceImpl implements PersonTypeService {

    @Override
    @Cacheable(Constants.PERSON_TYPES_IN_CACHE)
    public Optional<List<PersonType>> all() {
        List<PersonType> types = PersonType.getPersonTypes();
        return Optional.of(types);
    }

}