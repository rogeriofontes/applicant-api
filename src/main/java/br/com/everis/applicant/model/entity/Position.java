package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_position")
public class Position extends AuditModel {

    @Column(name = "description")
    private String description;

    @Column(name = "total_position")
    private int totalPosition;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    public void update(Long id, Position position) {
        super.setId(id);
        this.setDescription(position.getDescription());
        this.setTotalPosition(position.getTotalPosition());
        this.setProject(position.getProject());
        this.setProfessional(position.getProfessional());
    }

}
