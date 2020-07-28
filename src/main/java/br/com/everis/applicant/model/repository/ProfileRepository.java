package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, Serializable {

}
