package worldcup.service;

import worldcup.models.Representation;

import java.util.List;
import java.util.Optional;

public interface RepresentationService {
    List<Representation> findAll();

    List<Representation> findTopTwo();

    Optional<Representation> findOneById(Long id);

    Representation save(Representation representation);

    Representation delete(Long id);
}
