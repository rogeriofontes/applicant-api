/**
 * 
 */
package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Rogério Fontes
 *
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_team")
public class Team extends AuditModel {

	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "project_id" )
	private Project project;

	@ManyToOne
	@JoinColumn(name = "leader_id" )
	private Manager leader;

	public void update(Long id, Team team) {
		super.setId(id);
		this.setDescription(team.getDescription());
		this.setProject(team.getProject());
		this.setLeader(team.leader);
	}

}
