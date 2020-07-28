package br.com.everis.applicant.model.service;

import br.com.everis.applicant.model.entity.DocumentRegion;

import java.util.List;
import java.util.Optional;

public interface DocumentRegionService {
    Optional<List<DocumentRegion>> all();
}
