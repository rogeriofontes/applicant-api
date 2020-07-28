package br.com.everis.applicant.web.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class CountryResponse implements Serializable {
	private Long id;
	private String description;
}
