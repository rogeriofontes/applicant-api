package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
@Table(name = "tb_applicant")
@Inheritance(strategy = InheritanceType.JOINED)
public class Applicant extends Person {

	@Column(name = "linked_in")
	private String linkedIn;

	@Column(name = "registered_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime registeredAt;

	//@OneToMany(mappedBy = "applicant")
	//private Set<ApplicationEvaluation> evaluations;

	@Builder
	public Applicant(String name, Boolean hasNotebook, String motherName, String dateBirth, User user, String phone, String mobile, String email, String linkedIn, LocalDateTime registeredAt, Set<ApplicationEvaluation> evaluations) {
		super(name, hasNotebook, motherName, dateBirth, user, phone, mobile, email);
		this.linkedIn = linkedIn;
		this.registeredAt = registeredAt;
		//this.evaluations = evaluations;
	}

	public void update(Long id, Applicant applicant) {
		super.setId(id);
		this.setName(applicant.getName());
		this.setLinkedIn(applicant.getLinkedIn());
		this.setRegisteredAt(applicant.getRegisteredAt());
		this.setHasNotebook(applicant.getHasNotebook());
		this.setMotherName(applicant.getMotherName());
		this.setDateBirth(applicant.getDateBirth());
		this.setUser(applicant.getUser());
		this.setPhone(applicant.getPhone());
		this.setMobile(applicant.getMobile());
	}
}
