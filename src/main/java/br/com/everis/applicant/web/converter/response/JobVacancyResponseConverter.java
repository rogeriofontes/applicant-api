package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.JobVacancy;
import br.com.everis.applicant.web.dto.response.JobVacancyResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JobVacancyResponseConverter implements Converter<JobVacancy, JobVacancyResponse> {

    @Override
    public JobVacancyResponse convert(JobVacancy jobVacancy) {
        return JobVacancyResponse.builder()
                .id(jobVacancy.getId())
                .description(jobVacancy.getDescription())
                .team(jobVacancy.getTeam())
                .homeDate(jobVacancy.getValidateDate())
                .build();
    }
}