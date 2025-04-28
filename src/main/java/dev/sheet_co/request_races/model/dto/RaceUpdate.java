package dev.sheet_co.request_races.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class RaceUpdate {
  @NotNull
  private JsonNullable<String> name;
}
