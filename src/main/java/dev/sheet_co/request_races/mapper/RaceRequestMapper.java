package dev.sheet_co.request_races.mapper;

import dev.sheet_co.request_races.model.dto.RaceRequestCreateIn;
import dev.sheet_co.request_races.model.dto.RaceRequestOut;
import dev.sheet_co.request_races.model.dto.RaceRequestUpdateIn;
import dev.sheet_co.request_races.model.entity.RaceRequest;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {JsonNullableMapper.class}
)
public abstract class RaceRequestMapper {

  public abstract RaceRequestOut toResponse(RaceRequest entity);

  public abstract RaceRequest toEntity(RaceRequestCreateIn dto);

  public abstract void updateEntity(@MappingTarget RaceRequest entity, RaceRequestUpdateIn dto);
}
