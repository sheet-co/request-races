package dev.sheet_co.request_races.controller;

import dev.sheet_co.request_races.model.dto.RaceRequestDto;
import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.service.RaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race")
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<RaceRequestDto> getRace(@PathVariable String name) {
        var race = raceService.getRaceFromDb(name);
        RaceRequestDto raceRequestDto = new RaceRequestDto();
        BeanUtils.copyProperties(race, raceRequestDto);
        return ResponseEntity.ok(raceRequestDto);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RaceRequestDto create(@RequestBody Race race) {
        raceService.createRaceRequest(race);
        RaceRequestDto raceRequestDto = new RaceRequestDto();
        raceRequestDto.setName(race.getName());
        return raceRequestDto;
    }

    @GetMapping("/get-all")
        public List<Race> getAll(){
        return raceService.getAllRaceRequests();
    }


}


