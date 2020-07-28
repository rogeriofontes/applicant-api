package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.domain.Approved;
import br.com.everis.applicant.model.entity.*;
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
public class InterviewResponse implements Serializable {
    private JobVacancy jobVacancy;
    private Applicant applicant;
    private Professional professional;
    private String complementaryKnowledge;
    private String opinion;
    private String recommendation;
    private LocalDateTime interviewDate;
    private Approved approved;
}
