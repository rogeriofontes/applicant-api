package br.com.everis.applicant.model.repository;

import br.com.everis.applicant.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
                                                                                        
/**                                                                                     
 * @author alan.franco                                                                  
 *                                                                                      
 *         Classe de acesso aos dados de faixas de registro.                            
 *                                                                                      
 */                                                                                     
@Repository                                                                             
public interface CountryRepository extends JpaRepository<Country, Long>, Serializable {
                                                                                        
}                                                                                       
