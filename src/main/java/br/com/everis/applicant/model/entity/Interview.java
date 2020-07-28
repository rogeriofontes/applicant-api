package br.com.everis.applicant.model.entity;

import br.com.everis.applicant.model.domain.Approved;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_interview")
public class Interview extends AuditModel {

    @ManyToOne
    @JoinColumn(name = "job_vacancy_id")
    private JobVacancy jobVacancy;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @Column(name = "complementary_knowledge")
    private String complementaryKnowledge;

    private String opinion;

    private String recommendation;

    @NotNull
    @Column(name = "interview_date", nullable = false)
    private LocalDateTime interviewDate;

    @Column(name = "approved", nullable = false)
    @NotNull(message = "o campo \"approved\" é obrigario")
    @Enumerated(EnumType.STRING)
    private Approved approved = Approved.NOT_APPROVED;

    public void update(Long id, Interview interview) {
        super.setId(id);
        this.setJobVacancy(interview.getJobVacancy());
        this.setApplicant(interview.getApplicant());
        this.setProfessional(interview.getProfessional());
        this.setInterviewDate(interview.getInterviewDate());
        this.setComplementaryKnowledge(interview.getComplementaryKnowledge());
        this.setOpinion(interview.getOpinion());
        this.setRecommendation(interview.getRecommendation());
        this.setInterviewDate(interview.getInterviewDate());
        this.setApproved(interview.getApproved());
    }

}
