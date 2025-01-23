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
    public Optional<Representation> findOneById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Representation save(Representation representation) {
        return repository.save(representation);
    }

    @Override
    public Representation delete(Long id) {
        Representation foundRepresentation = repository.getReferenceById(id);
        repository.deleteById(foundRepresentation.getId());
        return foundRepresentation;
    }
}
