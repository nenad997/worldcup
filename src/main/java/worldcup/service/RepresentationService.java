package worldcup.service;

import worldcup.models.Representation;

import java.util.List;

public interface RepresentationService {
    List<Representation> findAll();

    List<Representation> findTopTwo();

    Representation findOneById(Long id);

    Representation save(Representation representation);

    boolean delete(Long id);
}
