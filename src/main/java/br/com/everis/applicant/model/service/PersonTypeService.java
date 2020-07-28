package br.com.everis.applicant.model.service;

import br.com.everis.applicant.model.entity.PersonType;

import java.util.List;
import java.util.Optional;

public interface PersonTypeService {
    Optional<List<PersonType>> all();
}
