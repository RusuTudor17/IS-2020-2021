package RusuTudor.demo.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotBlank(message = "Emailul nu poate fi gol.")
    @Email(message = "Emailul introdus nu este conform tipului de mail.")
    private String email;


    @NotBlank(message = "Parola nu poate fi goala.")
    @Column(length = 64)
    @Size(min=5,max=64,message="Parola trebuie sa fie de minim 5 charactere")
    private String password;


    @ManyToOne
    private Role rol;

    public User(@NotBlank(message = "Emailul nu poate fi gol.") @Email(message = "Emailul introdus nu este conform tipului de mail.") String email, @NotBlank(message = "Parola nu poate fi goala.") @Size(min = 5, max = 64, message = "Parola trebuie sa fie de minim 5 charactere") String password, Role rol) {
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public User(){

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

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}
