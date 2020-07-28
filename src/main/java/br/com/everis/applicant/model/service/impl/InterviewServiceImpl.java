package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Interview;
import br.com.everis.applicant.model.repository.InterviewRepository;
import br.com.everis.applicant.model.service.InterviewService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private InterviewRepository interviewRepository;

    public InterviewServiceImpl(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    public Interview save(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public Interview edit(Long id, Interview interview) {
        interview.update(id, interview);
        return interviewRepository.save(interview);
    }

    @Override
    @Cacheable(Constants.COUNTRYS_IN_CACHE)
    public List<Interview> listAll() {
        return interviewRepository.findAll();
    }

    @Override
    @Cacheable(Constants.COUNTRYS_IN_CACHE)
    public Page<Interview> findAllPageable(Pageable pageable) {
        return interviewRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.COUNTRYS_IN_CACHE)
    public Optional<Interview> findById(Long id) {
        return interviewRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            interviewRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
