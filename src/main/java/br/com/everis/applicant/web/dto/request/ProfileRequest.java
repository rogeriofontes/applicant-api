package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProfileRequest implements Serializable {
    private String role;
}
