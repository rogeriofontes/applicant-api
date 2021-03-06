package br.com.everis.applicant.web.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProfileResponse implements Serializable {
    private Long id;
    private String role;
}
