package br.com.everis.applicant.model.entity;

import br.com.everis.applicant.model.domain.Approved;
import br.com.everis.applicant.model.domain.TechLevel;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_applicant_evaluation")
@JsonTypeName(value = "applicant_evaluation")
public class ApplicationEvaluation extends AuditModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    @Column(name = "registered_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime registeredAt;

    @Column(name = "tech_level", nullable = false)
    @NotNull(message = "o campo \"approved\" é obrigario")
    @Enumerated(EnumType.STRING)
    private TechLevel techLevel = TechLevel.LOW;

    public void update(Long id, ApplicationEvaluation applicationEvaluation) {
        this.setId(id);
        this.setApplicant(applicationEvaluation.getApplicant());
        this.setProgrammingLanguage(applicationEvaluation.getProgrammingLanguage());
        this.setRegisteredAt(applicationEvaluation.getRegisteredAt());
    }
}
