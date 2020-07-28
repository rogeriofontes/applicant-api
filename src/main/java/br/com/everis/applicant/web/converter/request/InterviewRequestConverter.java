package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.exceptions.ResourceNotFoundException;
import br.com.everis.applicant.model.entity.*;
import br.com.everis.applicant.model.service.ApplicantService;
import br.com.everis.applicant.model.service.JobVacancyService;
import br.com.everis.applicant.model.service.ProfessionalService;
import br.com.everis.applicant.model.service.TeamService;
import br.com.everis.applicant.web.dto.request.InterviewRequest;
import br.com.everis.applicant.web.dto.request.JobVacancyRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Component
public class InterviewRequestConverter implements Converter<InterviewRequest, Interview> {

    private JobVacancyService jobVacancyService;
    private ApplicantService applicantService;
    private ProfessionalService professionalService;

    public InterviewRequestConverter(JobVacancyService jobVacancyService,
                                     ApplicantService applicantService,
                                     ProfessionalService professionalService) {
        this.jobVacancyService = jobVacancyService;
        this.applicantService = applicantService;
        this.professionalService = professionalService;
    }

    @Override
    public Interview convert(InterviewRequest request) {
        Optional<JobVacancy> jobVacancy = jobVacancyService.findById(request.getJobVacancyId());
        if(!jobVacancy.isPresent()) {
            throw new ResourceNotFoundException("JobVacancy does not exist!");
        }

        Optional<Applicant> applicant = applicantService.findById(request.getApplicantId());
        if(!applicant.isPresent()) {
            throw new ResourceNotFoundException("Applicant does not exist!");
        }

        Optional<Professional> professional = professionalService.findById(request.getApplicantId());
        if(!professional.isPresent()) {
            throw new ResourceNotFoundException("Professional does not exist!");
        }

        return Interview.builder()
                .jobVacancy(jobVacancy.isPresent() ? jobVacancy.get() : new JobVacancy())
                .applicant(applicant.isPresent() ? applicant.get() : new Applicant())
                .professional(professional.isPresent() ? professional.get() : new Professional())
                .complementaryKnowledge(request.getComplementaryKnowledge())
                .opinion(request.getOpinion())
                .recommendation(request.getRecommendation())
                .interviewDate(request.getInterviewDate())
                .approved(request.getApproved())
                .build();
    }
}