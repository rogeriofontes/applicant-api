package br.com.everis.applicant.web.dto.response;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "name", "acronym", "code" })
@Data
public class DocumentRegionResponse implements Serializable {
    private static final long serialVersionUID = 5661601145709869372L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String acronym;

    @Getter
    @Setter
    private String code;

}