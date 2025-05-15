package dev.sheet_co.request_races.exception;

import jakarta.persistence.EntityNotFoundException;

public class RaceRequestNotFoundException extends EntityNotFoundException {

  public RaceRequestNotFoundException(String message) {

    super(message);
  }
}
