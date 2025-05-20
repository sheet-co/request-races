package dev.sheet_co.request_races.service;

import dev.sheet_co.request_races.exception.NameAlreadyExistsException;
import dev.sheet_co.request_races.exception.RaceRequestNotFoundException;
import dev.sheet_co.request_races.mapper.RaceRequestMapper;
import dev.sheet_co.request_races.model.dto.RaceRequestCreateIn;
import dev.sheet_co.request_races.model.dto.RaceRequestOut;
import dev.sheet_co.request_races.model.dto.RaceRequestUpdateIn;
import dev.sheet_co.request_races.repository.RaceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RaceRequestService {

  private final RaceRequestRepository raceRequestRepository;
  private final RaceRequestMapper raceRequestMapper;

  @Autowired
  public RaceRequestService(RaceRequestRepository raceRequestRepository, RaceRequestMapper raceRequestMapper) {
    this.raceRequestRepository = raceRequestRepository;
    this.raceRequestMapper = raceRequestMapper;
  }

  public RaceRequestOut getRaceById(Long id) {
    var race = raceRequestRepository.findById(id)
                                    .orElseThrow(() -> new RaceRequestNotFoundException(id.toString()));
    return raceRequestMapper.toResponse(race);
  }

  public List<RaceRequestOut> getAllRaces() {
    return raceRequestRepository.findAll().stream()
                                .map(raceRequestMapper::toResponse)
                                .toList();
  }

  public RaceRequestOut createRace(RaceRequestCreateIn request) {
    var race = raceRequestMapper.toEntity(request);
    var searchName = raceRequestRepository.findRaceByName(race.getName());
    if (searchName.isPresent()) {
      throw new NameAlreadyExistsException("");
    }

    var savedRace = raceRequestRepository.save(race);
    return raceRequestMapper.toResponse(savedRace);
  }

  public RaceRequestOut updateRace(Long id, RaceRequestUpdateIn request) {
    var race = raceRequestRepository.findById(id)
                                    .orElseThrow(() -> new RaceRequestNotFoundException(id.toString()));
    raceRequestMapper.updateEntity(race, request);
    var updatedRace = raceRequestRepository.save(race);
    return raceRequestMapper.toResponse(updatedRace);

  }

  @Transactional
  public void deleteRace(Long id) {
    var race = raceRequestRepository.findById(id)
                                    .orElseThrow(() -> new RaceRequestNotFoundException(id.toString()));
    raceRequestRepository.delete(race);
  }

}
