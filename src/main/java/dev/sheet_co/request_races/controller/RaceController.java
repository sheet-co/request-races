package dev.sheet_co.request_races.controller;

import dev.sheet_co.request_races.model.dto.RaceRequestDto;
import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.service.RaceRequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/race")
public class RaceController {

  private final RaceRequestService raceRequestService;

  @Autowired
  public RaceController(RaceRequestService raceRequestService) {
    this.raceRequestService = raceRequestService;
  }

  @GetMapping("/{name}")
  public ResponseEntity<RaceRequestDto> getRace(@PathVariable String name) {
    var race = raceRequestService.getRaceFromDb(name);
    RaceRequestDto raceRequestDto = new RaceRequestDto();
    BeanUtils.copyProperties(race, raceRequestDto);
    return ResponseEntity.ok(raceRequestDto);
  }

  @PostMapping("/create")
  public ResponseEntity<RaceRequestDto> create(@RequestBody Race race) {
    raceRequestService.createRaceRequest(race);
    RaceRequestDto raceRequestDto = new RaceRequestDto();
    BeanUtils.copyProperties(race, raceRequestDto);
    return ResponseEntity.ok(raceRequestDto);
  }


}


