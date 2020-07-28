package br.com.everis.applicant.web.dto.request;

import br.com.everis.applicant.model.domain.Approved;
import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class InterviewRequest implements Serializable {
    private Long jobVacancyId;
    private Long applicantId;
    private Long professionalId;
    private String complementaryKnowledge;
    private String opinion;
    private String recommendation;
    private LocalDateTime interviewDate;
    private Approved approved;
}
