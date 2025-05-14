package dev.sheet_co.request_races.mapper;

import dev.sheet_co.request_races.model.dto.RaceCreateIn;
import dev.sheet_co.request_races.model.dto.RaceOut;
import dev.sheet_co.request_races.model.dto.RaceUpdateIn;
import dev.sheet_co.request_races.model.entity.RaceRequest;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {JsonNullableMapper.class}
)
public abstract class RaceMapper {

  public abstract RaceOut toResponse(RaceRequest entity);

  public abstract RaceRequest toEntity(RaceCreateIn dto);

  public abstract void updateEntity(@MappingTarget RaceRequest entity, RaceUpdateIn dto);
}
