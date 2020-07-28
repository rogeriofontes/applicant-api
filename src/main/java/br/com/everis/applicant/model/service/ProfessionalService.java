package br.com.everis.applicant.model.service;

import br.com.everis.applicant.model.entity.Professional;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessionalService extends BaseService<Professional, Long> {
    List<Professional> findByTeamId(@Param("teamId") Long teamId);
}
