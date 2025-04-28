package dev.sheet_co.request_races.controller;

import dev.sheet_co.request_races.model.dto.RaceCreateRequest;
import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//    @GetMapping("/{name}")
//    public ResponseEntity<RaceCreateRequest> getRace(@PathVariable String name) {
//        var race = raceService.getRaceFromDb(name);
//        if (race == null) {
//            return ResponseEntity.notFound().build();
//        }
//        RaceCreateRequest raceCreateRequest = new RaceCreateRequest();
//        raceCreateRequest.setName(race.getName());
//        return ResponseEntity.ok(raceCreateRequest);
//    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RaceCreateRequest create(@RequestBody Race race) {
        raceService.createRaceRequest(race);
        RaceCreateRequest raceCreateRequest = new RaceCreateRequest();
        raceCreateRequest.setName(race.getName());
        return raceCreateRequest;
    }

    @GetMapping()
    public List<Race> getAll() {
        return raceService.getAllRaceRequests();
    }


}


