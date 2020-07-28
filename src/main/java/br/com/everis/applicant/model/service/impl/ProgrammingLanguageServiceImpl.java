package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.model.repository.ProgrammingLanguageRepository;
import br.com.everis.applicant.model.service.ProgrammingLanguageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private ProgrammingLanguageRepository programmingLanguageRepository;

    public ProgrammingLanguageServiceImpl(ProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public ProgrammingLanguage save(ProgrammingLanguage programmingLanguage) {
        return programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    public ProgrammingLanguage edit(Long id, ProgrammingLanguage programmingLanguage) {
        programmingLanguage.update(id, programmingLanguage);
        return programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    @Cacheable(Constants.PROGRAMMING_LANGUAGE_IN_CACHE)
    public List<ProgrammingLanguage> listAll() {
        return programmingLanguageRepository.findAll();
    }

    @Override
    @Cacheable(Constants.PROGRAMMING_LANGUAGE_IN_CACHE)
    public Page<ProgrammingLanguage> findAllPageable(Pageable pageable) {
        return programmingLanguageRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.PROGRAMMING_LANGUAGE_IN_CACHE)
    public Optional<ProgrammingLanguage> findById(Long id) {
        return programmingLanguageRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            programmingLanguageRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
