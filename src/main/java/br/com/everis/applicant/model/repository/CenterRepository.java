package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcelo
 *
 */
@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

}
