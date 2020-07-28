package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of =  { "email", "password"})
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 4023504641313778939L;

    private String email;
    private String password;
}
