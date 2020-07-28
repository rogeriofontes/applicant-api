package br.com.everis.applicant.web.dto.request;

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
public class ProjectRequest implements Serializable {
    private String description;
    private Long centerId;
    private Long leaderId;
}
