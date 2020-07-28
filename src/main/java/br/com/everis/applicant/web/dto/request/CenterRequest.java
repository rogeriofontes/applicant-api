package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class CenterRequest implements Serializable {
    private String description;
    private Long countryId;
    private String state;
    private String city;
}
