package worldcup.service;

import worldcup.models.Footballer;

import java.util.List;
import java.util.Optional;

public interface FootballerService {
    List<Footballer> findAll();

    List<Footballer> findByYearOfBirth(Integer year);

    List<Footballer> findByMatchesBetween(int start, int end);

    Optional<Footballer> findOneById(Long id);

    Footballer save(Footballer footballer);

    Footballer edit(Long id, Footballer footballer);

    Footballer delete(Long id);
}
