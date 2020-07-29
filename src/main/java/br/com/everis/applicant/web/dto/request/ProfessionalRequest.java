package br.com.everis.applicant.web.dto.request;

import br.com.everis.applicant.model.entity.ProgrammingLanguage;
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
public class ProfessionalRequest implements Serializable {
	private String name;
	private Boolean hasNotebook;
	private Long teamId;
	private String motherName;
	private String dateBirth;
	private Long userId;
	private String phone;
	private String mobile;
	private String email;
	private Set<ProgrammingLanguage> programmingLanguages;
}
