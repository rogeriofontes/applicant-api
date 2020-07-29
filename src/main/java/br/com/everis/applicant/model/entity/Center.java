/**
 *
 */
package br.com.everis.applicant.model.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Rogério Fontes
 *
 */
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_center")
@JsonTypeName(value = "center")
public class Center extends AuditModel {

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id" )
    private Country country;

    private String state;

    private String city;

    public void update(Long id, Center center) {
        super.setId(id);
        this.setDescription(center.getDescription());
        this.setCountry(center.getCountry());
        this.setState(center.getState());
        this.setCity(center.getCity());
    }
}
