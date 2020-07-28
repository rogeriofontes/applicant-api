package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Position;
import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.model.entity.Project;
import br.com.everis.applicant.model.service.ProfessionalService;
import br.com.everis.applicant.model.service.ProjectService;
import br.com.everis.applicant.web.dto.request.PositionRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PositionRequestConverter implements Converter<PositionRequest, Position> {

    private ProjectService projectService;
    private ProfessionalService professionalService;

    public PositionRequestConverter(ProjectService projectService, ProfessionalService professionalService) {
        this.projectService = projectService;
        this.professionalService = professionalService;
    }

    @Override
    public Position convert(PositionRequest request) {
        Optional<Project> project = projectService.findById(request.getProjectId());
        Optional<Professional> professional = professionalService.findById(request.getProfessionalId());

        return Position.builder()
                .description(request.getDescription())
                .project(project.isPresent() ? project.get(): new Project())
                .professional(professional.isPresent() ? professional.get(): new Professional())
                .description(request.getDescription())
                .totalPosition(request.getTotalPosition()).build();
    }
}