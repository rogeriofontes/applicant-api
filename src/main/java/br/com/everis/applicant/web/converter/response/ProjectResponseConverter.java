package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Project;
import br.com.everis.applicant.web.dto.response.ProjectResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectResponseConverter implements Converter<Project, ProjectResponse> {

    @Override
    public ProjectResponse convert(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .description(project.getDescription())
                .center(project.getCenter())
                .leader(project.getLeader())
                .build();
    }
}