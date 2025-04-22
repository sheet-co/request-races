package dev.sheet_co.request_races.model.dto;

import dev.sheet_co.request_races.model.entity.Race;
import lombok.Value;

/**
 * DTO for {@link Race}
 */
@Value
public class RaceResponceDto {
  String name;
}