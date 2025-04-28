package dev.sheet_co.request_races.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RaceCreateRequest {
  @NotBlank
  private String name;

}
