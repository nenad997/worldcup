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
    public Footballer findOneById(Long id) {
        Optional<Footballer> foundFootballer = repository.findById(id);

        if (foundFootballer.isEmpty()) {
            throw new IllegalArgumentException("Footballer with id " + id + " not found!");
        }

        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @Override
    public Footballer save(Footballer footballer) {
        return repository.save(footballer);
    }

    @Override
    public Footballer edit(Long id, Footballer footballer) {
        Optional<Footballer> foundFootballer = repository.findById(id);

        if (foundFootballer.isEmpty()) {
            throw new IllegalArgumentException("You are trying to edit footballer which does not exist!");
        }

        Footballer toEdit = foundFootballer.get();

        toEdit.setFullName(footballer.getFullName());
        toEdit.setYearOfBirth(footballer.getYearOfBirth());
        toEdit.setNumberOfMatchesForRepresentation(footballer.getNumberOfMatchesForRepresentation());
        toEdit.setRepresentation(footballer.getRepresentation());

        return repository.save(toEdit);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Footballer> foundFootballer = repository.findById(id);

        if (foundFootballer.isEmpty()) {
            throw new IllegalArgumentException("You are trying to delete footballer which does not exist!");
        }

        repository.deleteById(foundFootballer.get().getId());
        return true;
    }
}
