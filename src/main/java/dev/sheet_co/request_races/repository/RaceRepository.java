package dev.sheet_co.request_races.repository;

import dev.sheet_co.request_races.model.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
  Optional<Race> findRaceByName(String raceName);
}
