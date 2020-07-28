package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Marcelo
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_project")
public class Project extends AuditModel {

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private Manager leader;

    public void update(Long id, Project project) {
        super.setId(id);
        this.setDescription(project.getDescription());
        this.setCenter(project.getCenter());
        this.setLeader(project.getLeader());
    }
}
