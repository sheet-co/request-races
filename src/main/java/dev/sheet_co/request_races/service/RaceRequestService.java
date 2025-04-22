package dev.sheet_co.request_races.service;

import dev.sheet_co.request_races.model.entity.RaceRequest;
import dev.sheet_co.request_races.repository.RaceRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class RaceRequestService {

  private final RaceRequestRepository raceRequestRepository;

  @Autowired
  public RaceRequestService(RaceRequestRepository raceRequestRepository) {
    this.raceRequestRepository = raceRequestRepository;
  }

  public void createRaceRequest(RaceRequest raceRequest) {
    String raceInDataBase = String.valueOf(raceRequestRepository.findRaceRequestByName(raceRequest.getName()));
    if (!raceInDataBase.equals(raceRequest.getName())) {
      raceRequestRepository.save(raceRequest);
      log.info("{} created", raceRequest.getName());
    }
    throw new IllegalArgumentException("Race with the name '" + raceRequest.getName() + "' already exists.");
  }

  public List<RaceRequest> getAllRaceRequests() {
    return raceRequestRepository.findAll();
  }

}
