package model.builder;

import model.Cont;
import model.Role;
import model.User;

import java.util.Date;
import java.util.List;

public class UserConstructor {
    /*

    private Long id;
    private String nume;
    private String parola;
    private List<Rol> roluri;

     */


    private User utilizator;

    public UserConstructor() {

        utilizator = new User();
    }

    public UserConstructor setId(Long Id)
    {
        utilizator.setId(Id);
        return this;
    }

    public UserConstructor setNume(String nume) {
        utilizator.setUsername(nume);
        return this;
    }

    public UserConstructor setParola(String parola) {
        utilizator.setPassword(parola);
        return this;
    }

    public UserConstructor setRoluri(List<Role> roluri) {
        utilizator.setRoles(roluri);
        return this;
    }

    public User build() {
        return utilizator;
    }
}
