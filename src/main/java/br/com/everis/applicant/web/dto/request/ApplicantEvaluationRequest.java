package br.com.everis.applicant.web.dto.request;

import br.com.everis.applicant.model.domain.TechLevel;
import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ApplicantEvaluationRequest implements Serializable {
	private Long applicantId;
	private Long programmingLanguageId;
	private TechLevel techLevel;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registeredAt;
}
