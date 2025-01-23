package worldcup.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import worldcup.util.dto.RepresentationDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "representation")
public class Representation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "abbr")
    private String abbr;

    @Column(name = "establishment_year")
    private Integer establishmentYear;

    @Column(name = "title")
    private Integer titles;

    @OneToMany(mappedBy = "representation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Footballer> footballers = new ArrayList<>();

    public Representation() {
    }

    public Representation(String name, String abbr, Integer establishmentYear, Integer titles) {
        this.name = name;
        this.abbr = abbr;
        this.establishmentYear = establishmentYear;
        this.titles = titles;
    }

    public Representation(RepresentationDTO dto) {
        this(dto.getName(), dto.getAbbr(), dto.getEstablishmentYear(), dto.getTitles());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Integer getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(Integer establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public Integer getTitles() {
        return titles;
    }

    public void setTitles(Integer titles) {
        this.titles = titles;
    }

    public List<Footballer> getFootballers() {
        return footballers;
    }

    public void setFootballers(List<Footballer> footballers) {
        this.footballers = footballers;
    }

    public void addFootballer(Footballer footballer) {
        if (!footballer.getRepresentation().equals(this)) {
            footballer.setRepresentation(this);
        }
        this.footballers.add(footballer);
    }
}
