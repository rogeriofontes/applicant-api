package br.com.everis.applicant.model.service.impl;


import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.ApplicationEvaluation;
import br.com.everis.applicant.model.repository.ApplicationEvaluationRepository;
import br.com.everis.applicant.model.service.ApplicationEvaluationService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Rogério Fontes
 *
 */
@Service
public class ApplicationEvaluationServiceImpl implements ApplicationEvaluationService {

    private ApplicationEvaluationRepository applicationEvaluationRepository;

    public ApplicationEvaluationServiceImpl(ApplicationEvaluationRepository applicationEvaluationRepository) {
        this.applicationEvaluationRepository = applicationEvaluationRepository;
    }

    @Override
    public ApplicationEvaluation save(ApplicationEvaluation applicationEvaluation) {
        return applicationEvaluationRepository.save(applicationEvaluation);
    }

    @Override
    public ApplicationEvaluation edit(Long id, ApplicationEvaluation applicationEvaluation) {
        applicationEvaluation.update(id, applicationEvaluation);
        return applicationEvaluationRepository.save(applicationEvaluation);
    }

    @Override
    @Cacheable(Constants.APPLICANT_EVALUATION_IN_CACHE)
    public List<ApplicationEvaluation> listAll() {
        return applicationEvaluationRepository.findAll();
    }

    @Override
    @Cacheable(Constants.APPLICANT_EVALUATION_IN_CACHE)
    public Page<ApplicationEvaluation> findAllPageable(Pageable pageable) {
        return applicationEvaluationRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.APPLICANT_EVALUATION_IN_CACHE)
    public Optional<ApplicationEvaluation> findById(Long id) {
        return applicationEvaluationRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            applicationEvaluationRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}