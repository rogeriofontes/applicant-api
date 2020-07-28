package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.model.entity.Center;
import br.com.everis.applicant.model.service.CenterService;
import br.com.everis.applicant.model.repository.CenterRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CenterServiceImpl implements CenterService {

    private CenterRepository centerRepository;

    public CenterServiceImpl(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public Center save(Center center) {
        return centerRepository.save(center);
    }

    @Override
    public Center edit(Long id, Center center) {
        return centerRepository.save(center);
    }

    @Override
    @Cacheable(Constants.CENTERS_IN_CACHE)
    public List<Center> listAll() {
        return centerRepository.findAll();
    }

    @Override
    @Cacheable(Constants.CENTERS_IN_CACHE)
    public Page<Center> findAllPageable(Pageable pageable) {
        return centerRepository.findAll(pageable);
    }

    @Override
    @Cacheable(Constants.CENTERS_IN_CACHE)
    public Optional<Center> findById(Long id) {
        return centerRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            centerRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
