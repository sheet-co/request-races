package dev.sheet_co.request_races.exception;

import jakarta.persistence.EntityNotFoundException;

public class RaceNotFoundByIdException extends EntityNotFoundException {

  public RaceNotFoundByIdException(Long id) {

    super("Race with id " + id + " not found");
  }
}
