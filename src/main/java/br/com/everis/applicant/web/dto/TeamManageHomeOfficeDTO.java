package br.com.everis.applicant.web.dto;

import br.com.everis.applicant.model.entity.Manager;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class TeamManageHomeOfficeDTO {
    private String developerName;
    private String teamName;
    private EmailDTO emailDTO;
    private List<String> scales;
    private Manager leader;
}
