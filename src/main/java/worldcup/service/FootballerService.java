package worldcup.service;

import worldcup.models.Footballer;

import java.util.List;

public interface FootballerService {
    List<Footballer> findAll();

    List<Footballer> findByYearOfBirth(Integer year);

    List<Footballer> findByMatchesBetween(int start, int end);

    Footballer findOneById(Long id);

    Footballer save(Footballer footballer);

    Footballer edit(Long id, Footballer footballer);

    boolean delete(Long id);
}
