package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.exceptions.ResourceNotFoundException;
import br.com.everis.applicant.model.entity.DocumentRegion;
import br.com.everis.applicant.model.entity.PersonType;
import br.com.everis.applicant.model.service.PersonTypeService;
import br.com.everis.applicant.web.dto.response.DocumentRegionResponse;
import br.com.everis.applicant.web.dto.response.PersonTypeResponse;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping(path = "/v1/person-types")
public class PersonTypeResources {

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<List<PersonTypeResponse>> get() {
        List<PersonTypeResponse> result = list();

        if (result != null) {
            log.info(Constants.TOTAL + result.size());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/acronym", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<PersonTypeResponse> find(@RequestParam("name") String val) {
        PersonType personType = Stream.of(PersonType.values()).filter(p -> p.name().equals(val)).findAny().orElseThrow(() -> new ResourceNotFoundException("PersonType not Found"));
        PersonTypeResponse saved = conversionService.convert(personType, PersonTypeResponse.class);
        return  ResponseEntity.ok(saved);
    }

    public List<PersonTypeResponse> list() {
        List<PersonTypeResponse> documentRegions = new ArrayList<>();
        Optional<List<PersonType>> list = personTypeService.all();

        if (list.isPresent()) {
            list.get().forEach(documentRegion -> {
                PersonTypeResponse saved = conversionService.convert(documentRegion, PersonTypeResponse.class);
                documentRegions.add(saved);
            });

            log.info("Size:" + list.get().size());
            return documentRegions;
        }

        return Collections.emptyList();
    }
}