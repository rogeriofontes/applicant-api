package br.com.everis.applicant.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author alan.franco
 *
 */
public interface BaseService<E, I extends Serializable> {
	E save(E e);
	E edit(I id, E e);
	List<E> listAll();
	Page<E> findAllPageable(Pageable pageable);
	Optional<E> findById(I id);
	boolean remove(I id);
}
