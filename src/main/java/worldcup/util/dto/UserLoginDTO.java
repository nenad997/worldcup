package worldcup.util.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotNull
    @NotBlank
    @Email(message = "Invalid email address provided!")
    private String emailAddress;

    @NotNull
    @NotBlank
    @Size(min = 7, max = 15, message = "Password must contain between 7 and 15 characters")
    private String password;

    public UserLoginDTO(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public @NotNull @NotBlank @Email(message = "Invalid email address provided!") String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NotNull @NotBlank @Email(message = "Invalid email address provided!") String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public @NotNull @NotBlank @Size(min = 7, max = 15, message = "Password must contain between 7 and 15 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotBlank @Size(min = 7, max = 15, message = "Password must contain between 7 and 15 characters") String password) {
        this.password = password;
    }
}
