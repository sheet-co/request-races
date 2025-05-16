package dev.sheet_co.request_races.controller;

import dev.sheet_co.request_races.model.dto.RaceRequestCreateIn;
import dev.sheet_co.request_races.model.dto.RaceRequestOut;
import dev.sheet_co.request_races.model.dto.RaceRequestUpdateIn;
import dev.sheet_co.request_races.service.RaceRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race-request")
public class RaceRequestController {

  private final RaceRequestService raceRequestService;

  @Autowired
  public RaceRequestController(RaceRequestService raceRequestService) {
    this.raceRequestService = raceRequestService;
  }

  @GetMapping
  public ResponseEntity<List<RaceRequestOut>> getAllRaces() {
    var races = raceRequestService.getAllRaces();
    return ResponseEntity.ok()
                         .header("X-Total-Count", String.valueOf(races.size()))
                         .body(races);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RaceRequestOut getRaceById(@PathVariable Long id) {
    return raceRequestService.getRaceById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RaceRequestOut createRace(@Valid @RequestBody RaceRequestCreateIn request) {
    return raceRequestService.createRace(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RaceRequestOut updateRace(@PathVariable Long id, @Valid @RequestBody RaceRequestUpdateIn request) {
    return raceRequestService.updateRace(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRace(@PathVariable Long id) {
    raceRequestService.deleteRace(id);
  }

}


