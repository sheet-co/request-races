package dev.sheet_co.request_races.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

  @ExceptionHandler(RaceRequestNotFoundException.class)
  public ProblemDetail handleRaceRequestNotFoundException(RaceRequestNotFoundException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problemDetail.setTitle("RaceRequestNotFoundException");
    return problemDetail;
  }
  @ExceptionHandler(NameAlreadyExistsException.class)
  public ProblemDetail handleNameAlreadyExistsException(NameAlreadyExistsException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    problemDetail.setTitle("NameAlreadyExistsException");
    return problemDetail;
  }
}
