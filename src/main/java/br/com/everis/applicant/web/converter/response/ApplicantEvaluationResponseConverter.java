package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.ApplicationEvaluation;
import br.com.everis.applicant.web.dto.response.ApplicationEvaluationResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicantEvaluationResponseConverter implements Converter<ApplicationEvaluation, ApplicationEvaluationResponse> {

    @Override
    public ApplicationEvaluationResponse convert(ApplicationEvaluation applicantEvaluation) {
        return ApplicationEvaluationResponse.builder()
                .id(applicantEvaluation.getId())
                .applicant(applicantEvaluation.getApplicant())
                .programmingLanguage(applicantEvaluation.getProgrammingLanguage())
                .registeredAt(applicantEvaluation.getRegisteredAt())
                .techLevel(applicantEvaluation.getTechLevel())
                .build();
    }
}