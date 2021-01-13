package RusuTudor.demo.models;

import RusuTudor.demo.models.validators.GameTypeConstraint;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
public class GameType extends AbstractEntity {


    @Size(min=2,max=15,message="Numele tipului de joc trebuie sa fie intre 2 si 15 de caractere.")
    @GameTypeConstraint
    private String name;

    @NotBlank(message = "Tipul de joc trebuie sa aiba o descriere")
    @Size(min = 5, max = 75, message = "Descrierea trebuie sa fie intre 5 si 75 de caractere.")
    private String description;



    @OneToMany(mappedBy ="gameType" )
    private List<Game> games;


    public GameType(@NotBlank(message = "Tipul de joc trebuie sa aiba un nume.") @Size(min = 5, max = 15, message = "Numele tipului de joc trebuie sa fie intre 5 si 15 de caractere.") String name, @NotBlank(message = "Tipul de joc trebuie sa aiba o descriere") @Size(min = 5, max = 75, message = "Descrierea trebuie sa fie intre 5 si 75 de caractere.") String description, List<Game> games) {
        this.name = name;
        this.description = description;
        this.games = games;
    }

    public GameType(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
