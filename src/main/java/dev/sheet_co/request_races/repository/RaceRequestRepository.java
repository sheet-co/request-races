package dev.sheet_co.request_races.repository;

import dev.sheet_co.request_races.model.entity.RaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRequestRepository extends JpaRepository<RaceRequest, Long> {
  RaceRequest findRaceRequestByName(String raceName);
}
