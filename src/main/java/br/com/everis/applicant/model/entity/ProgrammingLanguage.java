package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_programming_language")
public class ProgrammingLanguage extends AuditModel {

    @Column(name = "description")
    private String description;

    public void update(Long id, ProgrammingLanguage programmingLanguage) {
        super.setId(id);
        this.setDescription(programmingLanguage.getDescription());
    }
}
