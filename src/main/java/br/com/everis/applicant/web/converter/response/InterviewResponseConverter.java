package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Interview;
import br.com.everis.applicant.web.dto.response.InterviewResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InterviewResponseConverter implements Converter<Interview, InterviewResponse> {

    @Override
    public InterviewResponse convert(Interview interview) {
        return InterviewResponse.builder()
                .jobVacancy(interview.getJobVacancy())
                .applicant(interview.getApplicant())
                .professional(interview.getProfessional())
                .complementaryKnowledge(interview.getComplementaryKnowledge())
                .opinion(interview.getOpinion())
                .recommendation(interview.getRecommendation())
                .interviewDate(interview.getInterviewDate())
                .approved(interview.getApproved())
                .build();
    }
}