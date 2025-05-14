package dev.sheet_co.request_races.controller;

import dev.sheet_co.request_races.model.dto.RaceCreateIn;
import dev.sheet_co.request_races.model.dto.RaceOut;
import dev.sheet_co.request_races.model.dto.RaceUpdateIn;
import dev.sheet_co.request_races.service.RaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race-request")
public class RaceController {

  private final RaceService raceService;

  @Autowired
  public RaceController(RaceService raceService) {
    this.raceService = raceService;
  }

  @GetMapping
  public ResponseEntity<List<RaceOut>> getAllRaces() {
    var races = raceService.getAllRaces();
    return ResponseEntity.ok()
                         .header("X-Total-Count", String.valueOf(races.size()))
                         .body(races);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RaceOut getRaceById(@PathVariable Long id) {
    return raceService.getRaceById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RaceOut createRace(@Valid @RequestBody RaceCreateIn request) {
    return raceService.createRace(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RaceOut updateRace(@PathVariable Long id, @Valid @RequestBody RaceUpdateIn request) {
    return raceService.updateRace(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRace(@PathVariable Long id) {
    raceService.deleteRace(id);
  }

}


