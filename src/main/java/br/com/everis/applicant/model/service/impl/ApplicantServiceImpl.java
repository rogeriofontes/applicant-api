package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.repository.ApplicantRepository;
import br.com.everis.applicant.model.service.ApplicantService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcelo
 *
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private ApplicantRepository applicantRepository;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant edit(Long id, Applicant applicant) {
        applicant.update(id, applicant);
        return applicantRepository.save(applicant);
    }

    @Override
    @Cacheable(Constants.APPLICANT_IN_CACHE)
    public List<Applicant> listAll() {
        return applicantRepository.findAll();
    }

    @Override
    @Cacheable(Constants.APPLICANT_IN_CACHE)
    public Page<Applicant> findAllPageable(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.APPLICANT_IN_CACHE)
    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            applicantRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}