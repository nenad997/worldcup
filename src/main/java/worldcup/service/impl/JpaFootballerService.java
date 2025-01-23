package worldcup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.models.Footballer;
import worldcup.repository.FootballerRepository;
import worldcup.service.FootballerService;

import java.util.List;
import java.util.Optional;

@Service
public class JpaFootballerService implements FootballerService {
    private final FootballerRepository repository;

    @Autowired
    public JpaFootballerService(FootballerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Footballer> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Footballer> findByYearOfBirth(Integer year) {
        return repository.findByYearOfBirth(year);
    }

    @Override
    public List<Footballer> findByMatchesBetween(int start, int end) {
        return repository.findByMatchesBetween(start, end);
    }

    @Override
    public Optional<Footballer> findOneById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Footballer save(Footballer footballer) {
        return repository.save(footballer);
    }

    @Override
    public Footballer edit(Long id, Footballer footballer) {
        Footballer foundFootballer = repository.getReferenceById(id);

        foundFootballer.setFullName(footballer.getFullName());
        foundFootballer.setYearOfBirth(footballer.getYearOfBirth());
        foundFootballer.setNumberOfMatchesForRepresentation(footballer.getNumberOfMatchesForRepresentation());
        foundFootballer.setRepresentation(footballer.getRepresentation());

        return repository.save(foundFootballer);
    }

    @Override
    public Footballer delete(Long id) {
        Footballer foundFootballer = repository.getReferenceById(id);

        repository.deleteById(foundFootballer.getId());
        return foundFootballer;
    }
}
