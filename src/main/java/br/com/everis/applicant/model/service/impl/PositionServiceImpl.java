package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.service.PositionService;
import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Position;
import br.com.everis.applicant.model.repository.PositionRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position edit(Long id, Position position) {
        position.update(id, position);
        return positionRepository.save(position);
    }

    @Override
    @Cacheable(Constants.POSITION_IN_CACHE)
    public List<Position> listAll() {
        return positionRepository.findAll();
    }

    @Override
    @Cacheable(Constants.POSITION_IN_CACHE)
    public Page<Position> findAllPageable(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.POSITION_IN_CACHE)
    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            positionRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
