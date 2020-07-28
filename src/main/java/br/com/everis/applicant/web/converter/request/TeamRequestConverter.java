package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.model.entity.Project;
import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.service.ManagerService;
import br.com.everis.applicant.model.service.ProjectService;
import br.com.everis.applicant.web.dto.request.TeamRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeamRequestConverter implements Converter<TeamRequest, Team> {

    private ProjectService projectService;
    private ManagerService managerService;

    public TeamRequestConverter(ProjectService projectService, ManagerService managerService) {
        this.projectService = projectService;
        this.managerService = managerService;
    }

    @Override
    public Team convert(TeamRequest request) {
        Optional<Project> project = projectService.findById(request.getProjectId());
        Optional<Manager> leader = managerService.findById(request.getLeaderId());

        return Team.builder()
                .description(request.getDescription())
                .project(project.isPresent() ? project.get() : new Project())
                .leader(leader.isPresent() ? leader.get() : new Manager())
                .build();
    }
}