/**
 * 
 */
package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

/**
 * @author Marcelo
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class TeamSendEmailRequest implements Serializable {
	private Long teamId;
}
