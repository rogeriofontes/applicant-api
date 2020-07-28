package br.com.everis.applicant.web.resources;

import br.com.everis.applicant.constants.Constants;
import br.com.everis.applicant.exceptions.ResourceNotFoundException;
import br.com.everis.applicant.model.entity.DocumentRegion;
import br.com.everis.applicant.model.service.DocumentRegionService;
import br.com.everis.applicant.web.dto.response.DocumentRegionResponse;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping(path = "/v1/document-regions")
public class DocumentRegionResources {

    @Autowired
    private DocumentRegionService documentRegionService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @Timed
    public ResponseEntity<List<DocumentRegionResponse>> getAll() {
        List<DocumentRegionResponse> result = list();

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
    public ResponseEntity<DocumentRegionResponse> find(@RequestParam("name") String val) {
        DocumentRegion documentRegion = Stream.of(DocumentRegion.values()).filter(p -> p.name().equals(val)).findAny().orElseThrow(() -> new ResourceNotFoundException("PersonType not Found"));
        DocumentRegionResponse saved = conversionService.convert(documentRegion, DocumentRegionResponse.class);
        return  ResponseEntity.ok(saved);
    }

    private List<DocumentRegionResponse> list() {
        List<DocumentRegionResponse> documentRegions = new ArrayList<>();
        Optional<List<DocumentRegion>> list = documentRegionService.all();

        if (list.isPresent()) {
            list.get().forEach(documentRegion -> {
                DocumentRegionResponse saved = conversionService.convert(documentRegion, DocumentRegionResponse.class);
                documentRegions.add(saved);
            });

            log.info("Size:" + list.get().size());
            return documentRegions;
        }

        return Collections.emptyList();
    }
}
