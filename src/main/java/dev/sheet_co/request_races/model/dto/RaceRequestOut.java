package dev.sheet_co.request_races.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RaceRequestOut {
  private Long id;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("color")
  private String color;
}