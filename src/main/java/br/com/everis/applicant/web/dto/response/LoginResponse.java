package br.com.everis.applicant.web.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of =  { "userId", "token", "roles"})
public class LoginResponse {
    private String userId;
    private String token;
    private List<String> roles;
}
