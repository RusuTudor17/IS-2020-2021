package model;

import java.util.List;

public class User {

    private Long id;
    private String nume;
    private String parola;
    private List<Role> roluri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return nume;
    }

    public void setUsername(String username) {
        this.nume = username;
    }

    public String getPassword() {
        return parola;
    }

    public void setPassword(String password) {
        this.parola = password;
    }

    public List<Role> getRoles() {
        return roluri;
    }

    public void setRoles(List<Role> roluri) {
        this.roluri = roluri;
    }
}
