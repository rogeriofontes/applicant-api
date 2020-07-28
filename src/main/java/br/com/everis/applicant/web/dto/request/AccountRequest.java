package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AccountRequest implements Serializable {
    private String name;
    private String email;
    private String password;
    private Long registerNumber;
    private String userType;
}
