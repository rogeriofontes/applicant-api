package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marcelo
 *
 */
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
