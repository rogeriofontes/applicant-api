package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Rogério Fontes
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_manager")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "TYPE")
public class Manager extends Person {

	private int level;

	@Builder
	public Manager(String name, Boolean hasNotebook, String motherName, String dateBirth, User user, String phone, String mobile, String email, int level) {
		super(name, hasNotebook, motherName, dateBirth, user, phone, mobile, email);
		this.level = level;
	}

	public void update(Long id, Manager professional) {
		super.setId(id);
		this.setName(professional.getName());
		this.setHasNotebook(professional.getHasNotebook());
		this.setMotherName(professional.getMotherName());
		this.setDateBirth(professional.getDateBirth());
		this.setUser(professional.getUser());
		this.setPhone(professional.getPhone());
		this.setMobile(professional.getMobile());
		this.setLevel(professional.getLevel());
	}
}
