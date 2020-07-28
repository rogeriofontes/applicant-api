package br.com.everis.applicant.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class Person extends AuditModel {

    private String name;

    @Column(name = "has_notebook")
    private Boolean hasNotebook;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "date_birth")
    private String dateBirth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String phone;

    private String mobile;

    private String email;
}
