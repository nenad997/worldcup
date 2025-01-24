package worldcup.util.dto;

public class UserDetailsDTO {
    private String emailAddress;

    public UserDetailsDTO(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
