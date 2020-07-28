package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.exceptions.TeamNotFoundException;
import br.com.everis.applicant.exceptions.UserNotFoundException;
import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.TeamService;
import br.com.everis.applicant.model.service.UserService;
import br.com.everis.applicant.web.dto.request.ProfessionalRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfessionalRequestConverter implements Converter<ProfessionalRequest, Professional> {

    private TeamService teamService;
    private UserService userService;

    public ProfessionalRequestConverter(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @Override
    public Professional convert(ProfessionalRequest request) {
        Optional<Team> team = teamService.findById(request.getTeamId());
        if (!team.isPresent()) {
            throw new TeamNotFoundException("Team id does not exists!");
        }
        Optional<User> user = userService.findById(request.getUserId());
        if(!user.isPresent()) {
            throw new UserNotFoundException("User id does not exists!");
        }

        return Professional.builder()
                .name(request.getName())
                .hasNotebook(request.getHasNotebook())
                .motherName(request.getMotherName())
                .dateBirth(request.getDateBirth())
                .user(user.isPresent() ? user.get() : new User())
                .phone(request.getPhone())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .team(team.isPresent() ? team.get() : new Team())
                .programmingLanguages(request.getProgrammingLanguages())
                .build();
    }
}