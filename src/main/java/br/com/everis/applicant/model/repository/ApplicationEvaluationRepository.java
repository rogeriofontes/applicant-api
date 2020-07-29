package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.ApplicationEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rogério Fontes Tomaz
 *
 */
@Repository
public interface ApplicationEvaluationRepository extends JpaRepository<ApplicationEvaluation, Long> {
}
