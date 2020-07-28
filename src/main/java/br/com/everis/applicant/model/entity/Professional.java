package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Rogério Fontes
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_professional")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "TYPE")
public class Professional extends Person {

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToMany
	@JoinTable(name = "tb_professional_programing_language",
			joinColumns = @JoinColumn(name = "professional_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "programing_language_id", referencedColumnName = "id"))
	private Set<ProgrammingLanguage>  programmingLanguages;

	@Builder
	public Professional(String name, Boolean hasNotebook, String motherName, String dateBirth, User user, String phone, String mobile, String email, Team team, Set<ProgrammingLanguage>  programmingLanguages) {
		super(name, hasNotebook, motherName, dateBirth, user, phone, mobile, email);
		this.team = team;
		this.programmingLanguages = programmingLanguages;
	}

	public void update(Long id, Professional professional) {
		super.setId(id);
		this.setName(professional.getName());
		this.setTeam(professional.getTeam());
		this.setHasNotebook(professional.getHasNotebook());
		this.setMotherName(professional.getMotherName());
		this.setDateBirth(professional.getDateBirth());
		this.setUser(professional.getUser());
		this.setPhone(professional.getPhone());
		this.setMobile(professional.getMobile());
	}
}
