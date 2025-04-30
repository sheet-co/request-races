package dev.sheet_co.request_races.service;

import dev.sheet_co.request_races.exception.RaceNotFoundByIdException;
import dev.sheet_co.request_races.exception.RaceNotFoundByNameException;
import dev.sheet_co.request_races.mapper.RaceMapper;
import dev.sheet_co.request_races.model.dto.RaceCreateRequest;
import dev.sheet_co.request_races.model.dto.RaceResponse;
import dev.sheet_co.request_races.model.dto.RaceUpdateRequest;
import dev.sheet_co.request_races.model.entity.Race;
import dev.sheet_co.request_races.repository.RaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RaceService {

  private final RaceRepository raceRepository;
  private final RaceMapper raceMapper;

  @Autowired
  public RaceService(RaceRepository raceRepository, RaceMapper raceMapper) {
    this.raceRepository = raceRepository;
    this.raceMapper = raceMapper;
  }

  public RaceResponse getRaceByName(String name) {
    var race = raceRepository.findRaceByName(name)
                             .orElseThrow(() -> new RaceNotFoundByNameException(name));
    return raceMapper.toResponse(race);
  }

  public RaceResponse getRaceById(Long id) {
    var race = raceRepository.findById(id)
        .orElseThrow(()-> new RaceNotFoundByIdException(id));
    return raceMapper.toResponse(race);
  }

  public List<RaceResponse> getAllRaces() {
    return raceRepository.findAll().stream()
                         .map(raceMapper::toResponse)
                         .toList();
  }

  public RaceResponse createRace(RaceCreateRequest request) {
    var race = raceMapper.toEntity(request);
    var savedRace = raceRepository.save(race);
    return raceMapper.toResponse(savedRace);

  }

  public RaceResponse updateRace(Long id, RaceUpdateRequest request) {
    var race = raceRepository.findById(id)
                             .orElseThrow(() -> new RaceNotFoundByIdException(id));
    raceMapper.updateEntity(race, request);
    var updatedRace = raceRepository.save(race);
    return raceMapper.toResponse(updatedRace);

  }

  @Transactional
  public void deleteRace(Long id) {
    var race = raceRepository.findById(id)
                             .orElseThrow(() -> new RaceNotFoundByIdException(id));
    raceRepository.delete(race);
  }

  public Race getRaceFromDb(String name) {
    return raceRepository.findRaceByName(name)
                         .orElseThrow(() -> new RaceNotFoundByNameException(name));
  }

}
