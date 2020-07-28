package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProgrammingLanguageRequest implements Serializable {
    private String description;
}
