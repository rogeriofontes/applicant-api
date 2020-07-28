package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.model.entity.Project;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class PositionResponse implements Serializable {
    private Long id;
    private String description;
    private int totalPosition;
    private Project project;
    private Professional professional;
}
