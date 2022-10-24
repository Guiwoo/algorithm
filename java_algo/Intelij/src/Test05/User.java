package Test05;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime lastLogin;

    public User(){};

    public User(Long id, String email, String password, LocalDateTime lastLogin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void getSession(){
        //logic
    }
}
