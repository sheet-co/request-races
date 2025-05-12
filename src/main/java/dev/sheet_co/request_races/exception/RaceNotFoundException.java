package dev.sheet_co.request_races.exception;

import jakarta.persistence.EntityNotFoundException;

public class RaceNotFoundException extends EntityNotFoundException {

  public RaceNotFoundException(String message) {

    super(message);
  }
}
