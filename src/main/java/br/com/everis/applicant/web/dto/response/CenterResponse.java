package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Country;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class CenterResponse implements Serializable {
    private Long id;
    private String description;
    private Country country;
    private String state;
    private String city;
}
