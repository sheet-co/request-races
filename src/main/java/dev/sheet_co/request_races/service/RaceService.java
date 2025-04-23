package dev.sheet_co.request_races.service;

import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.repository.RaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RaceService {

  private final RaceRepository raceRepository;

  @Autowired
  public RaceService(RaceRepository raceRepository) {
    this.raceRepository = raceRepository;
  }

  public Race createRaceRequest(Race race) {
    String raceInDataBase = String.valueOf(raceRepository.findRaceRequestByName(race.getName()));
    if (!raceInDataBase.equals(race.getName())) {
      log.info("{} created", race.getName());
      return raceRepository.save(race);

    }
    throw new IllegalArgumentException("Race with the name '" + race.getName() + "' already exists.");
  }

  public List<Race> getAllRaceRequests() {
    return raceRepository.findAll();
  }

  public Race getRaceFromDb(String name) {
    return raceRepository.findRaceRequestByName(name);
  }

}
