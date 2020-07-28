package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.JobVacancy;
import br.com.everis.applicant.model.service.JobVacancyService;
import br.com.everis.applicant.model.repository.JobVacancyRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobVacancyServiceImpl implements JobVacancyService {

    private JobVacancyRepository jobVacancyRepository;

    public JobVacancyServiceImpl(JobVacancyRepository jobVacancyRepository) {
        this.jobVacancyRepository = jobVacancyRepository;
    }

    @Override
    public JobVacancy save(JobVacancy interview) {
        return jobVacancyRepository.save(interview);
    }

    @Override
    public JobVacancy edit(Long id, JobVacancy interview) {
        interview.update(id, interview);
        return jobVacancyRepository.save(interview);
    }

    @Override
    @Cacheable(Constants.MANAGE_HOME_OFFICE_IN_CACHE)
    public List<JobVacancy> listAll() {
        return jobVacancyRepository.findAll();
    }

    @Override
    @Cacheable(Constants.MANAGE_HOME_OFFICE_IN_CACHE)
    public Page<JobVacancy> findAllPageable(Pageable pageable) {
        return jobVacancyRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.MANAGE_HOME_OFFICE_IN_CACHE)
    public Optional<JobVacancy> findById(Long id) {
        return jobVacancyRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            jobVacancyRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<JobVacancy> findByTeamId(Long teamId) {
        return jobVacancyRepository.findByTeamId(teamId);
    }
}
