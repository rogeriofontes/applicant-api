package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.web.dto.response.TeamResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeamResponseConverter implements Converter<Team, TeamResponse> {

    @Override
    public TeamResponse convert(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .description(team.getDescription())
                .project(team.getProject())
                .leader(team.getLeader())
                .build();
    }
}