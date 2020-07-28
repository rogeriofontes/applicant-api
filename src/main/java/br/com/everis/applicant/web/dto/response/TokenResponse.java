package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Profile;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "userId", "token", "roles" })
@Data
public class TokenResponse {
    private Long userId;
    private String token;
    private Set<Profile> roles;
}
