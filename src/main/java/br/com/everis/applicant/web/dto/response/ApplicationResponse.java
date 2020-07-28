package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Team;
import br.com.everis.applicant.model.entity.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ApplicationResponse implements Serializable {
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
	private String linkedIn;
	private LocalDateTime registeredAt;
}
