package br.com.everis.applicant.model.service;

import br.com.everis.applicant.model.entity.JobVacancy;

import java.util.List;

public interface JobVacancyService extends BaseService<JobVacancy, Long> {
    List<JobVacancy> findByTeamId(Long teamId);
}
