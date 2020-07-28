package br.com.everis.applicant.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "tb_country")
public class Country extends AuditModel {

	private static final long serialVersionUID = -9158814171554795891L;

	@NotNull
	@Column(name = "description", nullable = true)
	private String description;

	public void update(Long id, Country country) {
		super.setId(id);
		this.setDescription(country.getDescription());
	}

}
