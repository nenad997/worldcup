package worldcup.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import worldcup.util.dto.FootballerDTO;

@Entity
@Table(name = "footballer")
public class Footballer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private Integer yearOfBirth;

    @Column(name = "num_matches_for_representation")
    private Integer numberOfMatchesForRepresentation;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "representation_id")
    private Representation representation;

    public Footballer() {
    }

    public Footballer(FootballerDTO dto) {
        this(dto.getFullName(), dto.getYearOfBirth(), dto.getNumberOfMatchesForRepresentation(), dto.getRepresentation());
    }

    public Footballer(String fullName, Integer yearOfBirth, Integer numberOfMatchesForRepresentation, Representation representation) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.numberOfMatchesForRepresentation = numberOfMatchesForRepresentation;
        this.representation = representation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getNumberOfMatchesForRepresentation() {
        return numberOfMatchesForRepresentation;
    }

    public void setNumberOfMatchesForRepresentation(Integer numberOfMatchesForRepresentation) {
        this.numberOfMatchesForRepresentation = numberOfMatchesForRepresentation;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public void setRepresentation(Representation representation) {
        if (!representation.getFootballers().contains(this)) {
            representation.getFootballers().add(this);
        }
        this.representation = representation;
    }
}
