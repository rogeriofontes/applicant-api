package br.com.everis.applicant.web.dto.response;

import br.com.everis.applicant.model.entity.Team;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class JobVacancyResponse implements Serializable {
    private Long id;
    private Team team;
    private String description;
    private LocalDateTime homeDate;
}
