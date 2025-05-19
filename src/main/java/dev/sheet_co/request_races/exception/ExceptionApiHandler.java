package dev.sheet_co.request_races.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
  @ExceptionHandler(RaceRequestNotFoundException.class)
  public ResponseEntity<ErrorMessage> raceRequestNotFoundException(RaceRequestNotFoundException ex){
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessage("RaceRequest not found"));
  }
}
