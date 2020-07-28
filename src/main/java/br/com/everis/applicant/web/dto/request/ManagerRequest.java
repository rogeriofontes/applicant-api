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
public class ManagerRequest implements Serializable {
	private String name;
	private Boolean hasNotebook;
	private Long teamId;
	private String motherName;
	private String dateBirth;
	private Long userId;
	private String phone;
	private String mobile;
	private String email;
	private int level;
}
