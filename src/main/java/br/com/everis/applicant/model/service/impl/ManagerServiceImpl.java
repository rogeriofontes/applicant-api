package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.service.ManagerService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.model.repository.ManagerRepository;
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
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager edit(Long id, Manager manager) {
        manager.update(id, manager);
        return managerRepository.save(manager);
    }

    @Override
    @Cacheable(Constants.PROFESSIONAL_IN_CACHE)
    public List<Manager> listAll() {
        return managerRepository.findAll();
    }

    @Override
    @Cacheable(Constants.PROFESSIONAL_IN_CACHE)
    public Page<Manager> findAllPageable(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.PROFESSIONAL_IN_CACHE)
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            managerRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}