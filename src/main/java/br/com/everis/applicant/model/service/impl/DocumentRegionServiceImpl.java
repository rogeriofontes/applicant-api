package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.DocumentRegion;
import br.com.everis.applicant.model.service.DocumentRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DocumentRegionServiceImpl implements DocumentRegionService {

    @Override
    @Cacheable(Constants.DOCUMENT_REGIONS_IN_CACHE)
    public Optional<List<DocumentRegion>> all() {
        List<DocumentRegion> regions= DocumentRegion.getDocumentRegions();
        return Optional.of(regions);
    }

}
