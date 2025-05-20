package dev.sheet_co.request_races.exception;

import jakarta.persistence.EntityExistsException;

public class NameAlreadyExistsException extends EntityExistsException {
  public NameAlreadyExistsException(String message){
    super(message);
  }
}
