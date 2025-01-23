package worldcup.util.dto;

import jakarta.validation.constraints.*;

public class RepresentationDTO {
    private Long id;

    @NotBlank(message = "Name field should not be empty")
    private String name;

    @NotNull(message = "Abbreviation should not be empty")
    @Size(min = 3, max = 3, message = "Abbreviation must be 3 characters long!")
    private String abbr;

    @NotNull(message = "Establishment year should not be empty")
    @Min(value = 1851, message = "Establishment year must be greater than 1850")
    @Max(value = 1999, message = "Establishment year must be less than 2000")
    private Integer establishmentYear;

    @NotNull(message = "Titles should not be empty")
    @Min(value = 0, message = "Min 0 titles")
    @Max(value = 5, message = "Max 5 titles")
    private Integer titles;

    public RepresentationDTO() {
    }

    public RepresentationDTO(String name, String abbr, Integer establishmentYear, Integer titles) {
        this.name = name;
        this.abbr = abbr;
        this.establishmentYear = establishmentYear;
        this.titles = titles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name field should not be empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name field should not be empty") String name) {
        this.name = name;
    }

    public @NotNull(message = "Abbreviation should not be empty") @Size(min = 3, max = 3, message = "Abbreviation must be 3 characters long!") String getAbbr() {
        return abbr;
    }

    public void setAbbr(@NotNull(message = "Abbreviation should not be empty") @Size(min = 3, max = 3, message = "Abbreviation must be 3 characters long!") String abbr) {
        this.abbr = abbr;
    }

    public @NotNull(message = "Establishment year should not be empty") @Min(value = 1851, message = "Establishment year must be greater than 1850") @Max(value = 1999, message = "Establishment year must be less than 2000") Integer getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(@NotNull(message = "Establishment year should not be empty") @Min(value = 1851, message = "Establishment year must be greater than 1850") @Max(value = 1999, message = "Establishment year must be less than 2000") Integer establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public @NotNull(message = "Titles should not be empty") @Min(value = 0, message = "Min 0 titles") @Max(value = 5, message = "Max 5 titles") Integer getTitles() {
        return titles;
    }

    public void setTitles(@NotNull(message = "Titles should not be empty") @Min(value = 0, message = "Min 0 titles") @Max(value = 5, message = "Max 5 titles") Integer titles) {
        this.titles = titles;
    }
}
