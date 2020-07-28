package br.com.everis.applicant.web.dto.request;

import br.com.everis.applicant.model.entity.ProgrammingLanguage;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class JobVacancyRequest implements Serializable {
    private Long teamId;
    private String title;
    private String description;
    private LocalDateTime validateDate;
    private Set<ProgrammingLanguage> programmingLanguages;
}
