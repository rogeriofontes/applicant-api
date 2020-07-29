package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.entity.User;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Rogério Fontes
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProfessionalResponse implements Serializable {
	private Long id;
	private String name;
	private Boolean hasNotebook;
	private Team team;
	private String motherName;
	private String dateBirth;
	private User user;
	private String phone;
	private String mobile;
	private String email;
	private Set<ProgrammingLanguage> programmingLanguages;
}
