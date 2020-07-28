package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Profile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(value = "userProfileRepository")
public interface UserProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByRole(@Param("role") String profile);
}
