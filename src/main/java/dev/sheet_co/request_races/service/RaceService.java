package dev.sheet_co.request_races.service;

import dev.sheet_co.request_races.exception.RaceNotFoundException;
import dev.sheet_co.request_races.mapper.RaceMapper;
import dev.sheet_co.request_races.model.dto.RaceCreateIn;
import dev.sheet_co.request_races.model.dto.RaceOut;
import dev.sheet_co.request_races.model.dto.RaceUpdateIn;
import dev.sheet_co.request_races.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RaceService {

  private final RaceRepository raceRepository;
  private final RaceMapper raceMapper;

  @Autowired
  public RaceService(RaceRepository raceRepository, RaceMapper raceMapper) {
    this.raceRepository = raceRepository;
    this.raceMapper = raceMapper;
  }

  public RaceOut getRaceById(Long id) {
    var race = raceRepository.findById(id)
                             .orElseThrow(() -> new RaceNotFoundException(id.toString()));
    return raceMapper.toResponse(race);
  }

  public List<RaceOut> getAllRaces() {
    return raceRepository.findAll().stream()
                         .map(raceMapper::toResponse)
                         .toList();
  }

  public RaceOut createRace(RaceCreateIn request) {
    var race = raceMapper.toEntity(request);
    var savedRace = raceRepository.save(race);
    return raceMapper.toResponse(savedRace);

  }

  public RaceOut updateRace(Long id, RaceUpdateIn request) {
    var race = raceRepository.findById(id)
                             .orElseThrow(() -> new RaceNotFoundException(id.toString()));
    raceMapper.updateEntity(race, request);
    var updatedRace = raceRepository.save(race);
    return raceMapper.toResponse(updatedRace);

  }

  @Transactional
  public void deleteRace(Long id) {
    var race = raceRepository.findById(id)
                             .orElseThrow(() -> new RaceNotFoundException(id.toString()));
    raceRepository.delete(race);
  }

}
