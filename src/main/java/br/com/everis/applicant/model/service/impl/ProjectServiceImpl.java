package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Project;
import br.com.everis.applicant.model.repository.ProjectRepository;
import br.com.everis.applicant.model.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project edit(Long id, Project project) {
        project.update(id, project);
        return projectRepository.save(project);
    }

    @Override
    @Cacheable(Constants.PROJECT_IN_CACHE)
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @Override
    @Cacheable(Constants.PROJECT_IN_CACHE)
    public Page<Project> findAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.PROJECT_IN_CACHE)
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            projectRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
