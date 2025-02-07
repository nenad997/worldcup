package worldcup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.models.Representation;
import worldcup.repository.RepresentationRepository;
import worldcup.service.RepresentationService;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRepresentationService implements RepresentationService {
    private final RepresentationRepository repository;

    @Autowired
    public JpaRepresentationService(RepresentationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Representation> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Representation> findTopTwo() {
        return repository.findTop2ByOrderByTitlesDesc();
    }

    @Override
    public Representation findOneById(Long id) {
        Optional<Representation> foundRepresentation = repository.findById(id);

        if (foundRepresentation.isEmpty()) {
            throw new IllegalArgumentException("Representation with id " + id + " not found!");
        }

        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @Override
    public Representation save(Representation representation) {
        return repository.save(representation);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Representation> foundRepresentation = repository.findById(id);

        if (foundRepresentation.isEmpty()) {
            throw new IllegalArgumentException("You are trying to delete representation which does not exist!");
        }

        repository.deleteById(foundRepresentation.get().getId());
        return true;
    }
}
