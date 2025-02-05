package worldcup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import worldcup.models.Footballer;
import worldcup.service.FootballerService;
import worldcup.util.dto.FootballerDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/footballers")
public class APIFootballers {
    private final FootballerService service;

    @Autowired
    public APIFootballers(FootballerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Footballer>> findAll(@RequestParam(required = false) String year) {
        if (year == null) {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        }

        try {
            int parsedYear = Integer.parseInt(year);
            return new ResponseEntity<>(service.findByYearOfBirth(parsedYear), HttpStatus.OK);
        } catch (NumberFormatException ex) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Footballer> findOne(@PathVariable Long id) {
        Footballer foundFootballer = service.findOneById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Footballer with id " + id + " not found!"));

        return new ResponseEntity<>(foundFootballer, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Footballer>> searchByMatchesForRepresentation(@RequestParam int start, @RequestParam int end) {
        if (start < 0 || end < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (start > end) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.findByMatchesBetween(start, end), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addFootballer(
            @Valid
            @RequestBody
            FootballerDTO dto,
            BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Footballer savedFootballer = service.save(new Footballer(dto));

        return new ResponseEntity<>(savedFootballer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editFootballer(
            @PathVariable
            Long id,
            @Valid
            @RequestBody FootballerDTO dto,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Footballer foundFootballer = service.findOneById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Footballer with id " + id + " not found!"));

        Footballer editedFootballer = service.edit(foundFootballer.getId(), new Footballer(dto));

        return new ResponseEntity<>(editedFootballer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Footballer> deleteFootballer(@PathVariable Long id) {
        Footballer foundFootballer = service.findOneById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Footballer with id " + id + " not found!"));

        Footballer deletedFootballer = service.delete(foundFootballer.getId());

        return new ResponseEntity<>(deletedFootballer, HttpStatus.OK);
    }
}
