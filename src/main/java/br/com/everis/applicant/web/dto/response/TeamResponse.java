/**
 * 
 */
package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.model.entity.Project;
import lombok.*;

import java.io.Serializable;

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
public class TeamResponse implements Serializable {
	private Long id;
	private String description;
	private Project project;
	private Manager leader;
}
