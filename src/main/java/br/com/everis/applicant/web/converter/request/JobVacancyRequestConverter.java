package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.JobVacancy;
import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.service.TeamService;
import br.com.everis.applicant.web.dto.request.JobVacancyRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobVacancyRequestConverter implements Converter<JobVacancyRequest, JobVacancy> {

    private TeamService teamService;

    public JobVacancyRequestConverter(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public JobVacancy convert(JobVacancyRequest request) {
        Optional<Team> team = teamService.findById(request.getTeamId());

        return JobVacancy.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .team(team.isPresent() ? team.get() : new Team())
                .validateDate(request.getValidateDate())
                .programmingLanguages(request.getProgrammingLanguages())
                .build();
    }
}