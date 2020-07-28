package br.com.everis.applicant.model.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_job_vacancy")
public class JobVacancy extends AuditModel {

    @ManyToOne
    @JoinColumn(name = "team_id" )
    private Team team;

    private String title;

    private String description;

    @NotNull
    @Column(name = "validate_date", nullable = false)
    private LocalDateTime validateDate;

    @ManyToMany
    @JoinTable(name = "tb_job_vacancy_programing_language",
            joinColumns = @JoinColumn(name = "job_vacancy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "programing_language_id", referencedColumnName = "id"))
    private Set<ProgrammingLanguage> programmingLanguages;

    public void update(Long id, JobVacancy jobVacancy) {
        super.setId(id);
        this.setTitle(jobVacancy.getTitle());
        this.setDescription(jobVacancy.getDescription());
        this.setTeam(jobVacancy.getTeam());
        this.setValidateDate(jobVacancy.getValidateDate());
    }

}
