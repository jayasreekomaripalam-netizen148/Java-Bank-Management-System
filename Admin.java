import java.io.Serializable;

public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Admin() {
        this.username = "admin";
        this.password = "admin123";
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}