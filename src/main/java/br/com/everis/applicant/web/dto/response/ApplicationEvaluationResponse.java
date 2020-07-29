package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.domain.TechLevel;
import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ApplicationEvaluationResponse implements Serializable {
	private Long id;
	private Applicant applicant;
	private ProgrammingLanguage programmingLanguage;
	private LocalDateTime registeredAt;
	private TechLevel techLevel;
}
