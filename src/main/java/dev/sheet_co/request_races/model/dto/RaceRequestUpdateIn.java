package dev.sheet_co.request_races.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class RaceRequestUpdateIn {
  private Long id;

  @NotNull
  @JsonProperty("name")
  private JsonNullable<String> name;

  @NotNull
  @JsonProperty("color")
  private JsonNullable<String> color;
}
