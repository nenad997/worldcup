package worldcup.util.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import worldcup.models.Representation;

public class FootballerDTO {
    private Long id;

    @NotNull(message = "Full name should not be empty")
    @Size(min = 5, max = 40, message = "Length must be between 5 and 40 characters")
    private String fullName;

    @NotNull(message = "Date of birth should not be empty")
    @Min(value = 1976, message = "Min 1976")
    @Max(value = 1999, message = "Max 1999")
    private Integer yearOfBirth;

    @NotNull(message = "Should not be empty")
    @Min(value = 1, message = "Value must be greater that 0")
    private Integer numberOfMatchesForRepresentation;

    @NotNull
    private Representation representation;

    public FootballerDTO() {
    }

    public FootballerDTO(String fullName, Integer yearOfBirth, Integer numberOfMatchesForRepresentation, Representation representation) {
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

    public @NotNull(message = "Full name should not be empty") @Size(min = 5, max = 40, message = "Length must be between 5 and 40 characters") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull(message = "Full name should not be empty") @Size(min = 5, max = 40, message = "Length must be between 5 and 40 characters") String fullName) {
        this.fullName = fullName;
    }

    public @NotNull(message = "Date of birth should not be empty") @Min(value = 1976, message = "Min 1976") @Max(value = 1999, message = "Max 1999") Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(@NotNull(message = "Date of birth should not be empty") @Min(value = 1976, message = "Min 1976") @Max(value = 1999, message = "Max 1999") Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public @NotNull(message = "Should not be empty") @Min(value = 1, message = "Value must be greater that 0") Integer getNumberOfMatchesForRepresentation() {
        return numberOfMatchesForRepresentation;
    }

    public void setNumberOfMatchesForRepresentation(@NotNull(message = "Should not be empty") @Min(value = 1, message = "Value must be greater that 0") Integer numberOfMatchesForRepresentation) {
        this.numberOfMatchesForRepresentation = numberOfMatchesForRepresentation;
    }

    public @NotNull Representation getRepresentation() {
        return representation;
    }

    public void setRepresentation(@NotNull Representation representation) {
        this.representation = representation;
    }
}
