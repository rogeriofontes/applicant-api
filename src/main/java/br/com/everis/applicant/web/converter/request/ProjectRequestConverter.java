package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Center;
import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.model.entity.Project;
import br.com.everis.applicant.model.service.CenterService;
import br.com.everis.applicant.model.service.ManagerService;
import br.com.everis.applicant.web.dto.request.ProjectRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectRequestConverter implements Converter<ProjectRequest, Project> {

    private CenterService centerService;
    private ManagerService managerService;

    public ProjectRequestConverter(CenterService centerService, ManagerService managerService) {
        this.centerService = centerService;
        this.managerService = managerService;
    }

    @Override
    public Project convert(ProjectRequest request) {
        Optional<Center> center = centerService.findById(request.getCenterId());
        Optional<Manager> manager = managerService.findById(request.getLeaderId());

        return Project.builder()
                .description(request.getDescription())
                .center(center.isPresent() ? center.get() : new Center())
                .leader(manager.isPresent() ? manager.get() : new Manager()).build();
    }
}