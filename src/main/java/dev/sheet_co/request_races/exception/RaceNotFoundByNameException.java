package dev.sheet_co.request_races.exception;

import jakarta.persistence.EntityNotFoundException;

public class RaceNotFoundByNameException extends EntityNotFoundException {

  public RaceNotFoundByNameException(String name) {

    super("Race with name " + name + " not found");
  }
}
