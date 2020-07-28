package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_profile")
public class Profile extends AuditModel {

    private String role;

    public void update(Long id, Profile profile) {
        super.setId(id);
        this.setRole(profile.getRole());
    }

}
