package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.repository.TeamRepository;
import br.com.everis.applicant.model.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team edit(Long id, Team team) {
        team.update(id, team);
        return teamRepository.save(team);
    }

    @Override
    @Cacheable(Constants.TEAM_IN_CACHE)
    public List<Team> listAll() {
        return teamRepository.findAll();
    }

    @Override
    @Cacheable(Constants.TEAM_IN_CACHE)
    public Page<Team> findAllPageable(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.TEAM_IN_CACHE)
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            teamRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
