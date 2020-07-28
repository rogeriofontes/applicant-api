package br.com.everis.applicant.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class PositionRequest implements Serializable {
    private String description;
    private int totalPosition;
    private Long projectId;
    private Long professionalId;
}
