package br.com.everis.applicant.model.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, of =  { "email", "password"})
public class AccountCredentials implements Serializable {
    private String username;
    private String password;
}
