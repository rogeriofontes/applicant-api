package br.com.everis.applicant.model.entity;

import br.com.everis.applicant.model.domain.Status;
import br.com.everis.applicant.util.DateUtil;
import br.com.everis.applicant.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author evandro
 */
@Data
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt, updatedAt"}, allowGetters = true,  ignoreUnknown = true)
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = 3661315543425633373L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdDate = DateUtil.convert(new Date());

    @NotNull
    @Column(name = "create_by")
    @JsonIgnore
    @CreatedBy
    private String createBy = Constants.CURRENT_USER;

    @Column(name = "last_modified_date")
    @JsonIgnore
    @LastModifiedDate
    @Getter
    @Setter
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_modified_by")
    @JsonIgnore
    @LastModifiedBy
    @Getter
    @Setter
    private String lastModifiedBy;

    @Column(name = "status", nullable = false)
    @javax.validation.constraints.NotNull(message = "o campo \"status\" é obrigario")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

}
