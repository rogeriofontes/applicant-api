package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Position;
import br.com.everis.applicant.web.dto.response.PositionResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionResponseConverter implements Converter<Position, PositionResponse> {

    @Override
    public PositionResponse convert(Position position) {
        return PositionResponse.builder()
                .id(position.getId())
                .description(position.getDescription())
                .professional(position.getProfessional())
                .project(position.getProject())
                .totalPosition(position.getTotalPosition())
                .build();
    }
}