package br.com.everis.applicant.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
public class ApplicantRequest implements Serializable {
	private String name;
	private Boolean hasNotebook;
	private Long teamId;
	private String motherName;
	private String dateBirth;
	private Long userId;
	private String phone;
	private String mobile;
	private String email;
	private String linkedIn;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registeredAt;
}
