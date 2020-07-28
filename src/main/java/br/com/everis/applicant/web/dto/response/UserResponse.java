package br.com.everis.applicant.web.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String password;
}
