package worldcup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import worldcup.models.Representation;
import worldcup.service.RepresentationService;
import worldcup.util.dto.RepresentationDTO;

import java.util.List;

@RestController
@RequestMapping("/api/representations")
public class APIRepresentations {
    private final RepresentationService service;

    @Autowired
    public APIRepresentations(RepresentationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Representation>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<List<Representation>> findTopTwo() {
        return new ResponseEntity<>(service.findTopTwo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Representation> findOne(
            @PathVariable
            Long id) {
        Representation foundRepresentation = service.findOneById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Representation with id " + id + "not found!"));

        return new ResponseEntity<>(foundRepresentation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addRepresentation(
            @Valid
            @RequestBody
            RepresentationDTO dto,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Representation savedRepresentation = service.save(new Representation(dto));

        return new ResponseEntity<>(savedRepresentation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Representation> deleteRepresentation(
            @PathVariable
            Long id) {
        Representation foundRepresentation = service.findOneById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The representation you are trying to delete does not exist!"));

        Representation deletedRepresentation = service.delete(foundRepresentation.getId());

        return new ResponseEntity<>(deletedRepresentation, HttpStatus.OK);
    }
}
