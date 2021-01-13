package RusuTudor.demo.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends AbstractEntity{
    private String roleName;

    @OneToMany(mappedBy = "rol")
    private List<User> users= new ArrayList<>();

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(){}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
