package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Center;
import br.com.everis.applicant.model.entity.Manager;
import lombok.*;

import java.io.Serializable;

/**
 * @author Marcelo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class ProjectResponse implements Serializable {
    private Long id;
    private String description;
    private Center center;
    private Manager leader;
}
