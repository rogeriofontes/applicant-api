package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobVacancyRepository extends JpaRepository<JobVacancy, Long> {
    List<JobVacancy> findByTeamId(@Param("teamId") Long teamId);
}
