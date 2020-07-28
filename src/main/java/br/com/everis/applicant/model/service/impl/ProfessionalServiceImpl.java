package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.model.service.ProfessionalService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.repository.ProfessionalRepository;
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
public class ProfessionalServiceImpl implements ProfessionalService {

    private ProfessionalRepository professionalRepository;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Professional save(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Override
    public Professional edit(Long id, Professional professional) {
        professional.update(id, professional);
        return professionalRepository.save(professional);
    }

    @Override
    @Cacheable(Constants.PROFESSIONAL_IN_CACHE)
    public List<Professional> listAll() {
        return professionalRepository.findAll();
    }

    @Override
    @Cacheable(Constants.PROFESSIONAL_IN_CACHE)
    public Page<Professional> findAllPageable(Pageable pageable) {
        return professionalRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.PROFESSIONAL_IN_CACHE)
    public Optional<Professional> findById(Long id) {
        return professionalRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            professionalRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<Professional> findByTeamId(Long teamId) {
        return professionalRepository.findByTeamId(teamId);
    }
}