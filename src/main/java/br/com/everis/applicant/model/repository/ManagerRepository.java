package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rogério Fontes
 *
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
