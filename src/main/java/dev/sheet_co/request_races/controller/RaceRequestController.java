package dev.sheet_co.request_races.controller;

import dev.sheet_co.request_races.model.dto.RaceRequestDto;
import dev.sheet_co.request_races.model.entity.RaceRequest;
import dev.sheet_co.request_races.service.RaceRequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/race")
public class RaceRequestController {

  private final RaceRequestService raceRequestService;

  @Autowired
  public RaceRequestController(RaceRequestService raceRequestService) {
    this.raceRequestService = raceRequestService;
  }

  @GetMapping("/get")
  public ResponseEntity<String> getRace() {

    return ResponseEntity.ok("RaceRequest information");
  }

  @PostMapping("/create")
  public ResponseEntity<RaceRequestDto> create(@RequestBody RaceRequest raceRequest) {
    raceRequestService.createRaceRequest(raceRequest);
    RaceRequestDto raceRequestDto = new RaceRequestDto();
    BeanUtils.copyProperties(raceRequest,raceRequestDto);
    return ResponseEntity.ok(raceRequestDto);
  }


}


