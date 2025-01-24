package worldcup.util.dto;

import jakarta.validation.constraints.*;
import worldcup.util.annotations.FieldMatch;

@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords do not match")
public class CreateUserDTO {
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 5, message = "Should be at least 5 characters long")
    private String firstName;
    private String lastName;

    @NotNull
    @NotBlank
    @Email(message = "Invalid email address provided!")
    private String emailAddress;

    @NotNull
    @NotBlank
    @Size(min = 7, max = 15, message = "Password must contain between 7 and 15 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{7,15}$", message = "Password must contain at least one uppercase letter and one number")
    private String password;

    private String repeatPassword;

    public CreateUserDTO(String firstName, String lastName, String emailAddress, String password, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @NotBlank @Size(min = 5, message = "First name should be at least 5 characters long") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @NotBlank @Size(min = 5, message = "First name should be at least 5 characters long") String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public @NotNull @NotBlank @Email(message = "Invalid email address provided!") String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NotNull @NotBlank @Email(message = "Invalid email address provided!") String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public @NotNull @NotBlank @Size(min = 7, max = 15, message = "Password must contain between 7 and 15 characters") @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{7,15}$", message = "Password must contain at least one uppercase letter and one number") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotBlank @Size(min = 7, max = 15, message = "Password must contain between 7 and 15 characters") @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{7,15}$", message = "Password must contain at least one uppercase letter and one number") String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
