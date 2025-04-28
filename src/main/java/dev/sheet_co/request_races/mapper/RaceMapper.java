package dev.sheet_co.request_races.mapper;

import dev.sheet_co.request_races.model.dto.RaceCreateRequest;
import dev.sheet_co.request_races.model.dto.RaceResponse;
import dev.sheet_co.request_races.model.entity.Race;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class RaceMapper {

  public abstract RaceResponse toResponse(Race entity);

  public abstract Race toEntity(RaceCreateRequest dto);

  public abstract void updateEntity(@MappingTarget Race entity, RaceCreateRequest dto);
}
