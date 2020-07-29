package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.exceptions.UserNotFoundException;
import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.ApplicationEvaluation;
import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.model.service.ApplicantService;
import br.com.everis.applicant.model.service.ProgrammingLanguageService;
import br.com.everis.applicant.web.dto.request.ApplicantEvaluationRequest;
import br.com.everis.applicant.web.dto.response.ApplicationEvaluationResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationEvaluationRequestConverter implements Converter<ApplicantEvaluationRequest, ApplicationEvaluation> {
    private ApplicantService applicantService;
    private ProgrammingLanguageService programmingLanguageService;

    public ApplicationEvaluationRequestConverter(ApplicantService applicantService, ProgrammingLanguageService programmingLanguageService) {
        this.applicantService = applicantService;
        this.programmingLanguageService = programmingLanguageService;
    }

    @Override
    public ApplicationEvaluation convert(ApplicantEvaluationRequest request) {
        Optional<Applicant> applicant = applicantService.findById(request.getApplicantId());
        if(!applicant.isPresent()) {
            throw new UserNotFoundException("User id does not exists!");
        }
        Optional<ProgrammingLanguage> programmingLanguage = programmingLanguageService.findById(request.getProgrammingLanguageId());
        if(!programmingLanguage.isPresent()) {
            throw new UserNotFoundException("User id does not exists!");
        }


        return ApplicationEvaluation.builder()
                .applicant(applicant.isPresent() ? applicant.get() : new Applicant())
                .techLevel(request.getTechLevel())
                .programmingLanguage(programmingLanguage.isPresent() ? programmingLanguage.get() : new ProgrammingLanguage())
                .registeredAt(request.getRegisteredAt())
                .build();
    }
}
