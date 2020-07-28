package br.com.everis.applicant.web.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EmailDTO {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String contentType;
}
