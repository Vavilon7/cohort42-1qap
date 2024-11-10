package app.web.demoShopSelenium.data;

public class Contact {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private String ConfirmPassword;

    public Contact(String firstName, String lastName, String email, String password, String confirmPassword) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
        ConfirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}
