package RusuTudor.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Game extends AbstractEntity{

    @NotBlank(message = "Jocul trebuie sa aiba un nume.")
    @Size(min=5,max=50,message="Numele jocului trebuie sa fie intre 5 si 50 de caractere.")
    private String name;

    @Size(min = 10 , max = 200, message = "Descrierea jocului trebuie sa aiba minim 10 si maxim 200 de caractere")
    private String description;

    @ManyToOne
    private GameType gameType;// create,update,read.

    public Game(@NotBlank(message = "Jocul trebuie sa aiba un nume.") @Size(min = 5, max = 50, message = "Numele jocului trebuie sa fie intre 5 si 50 de caractere.") String name, @Size(min = 10, max = 200, message = "Descrierea jocului trebuie sa aiba minim 10 si maxim 200 de caractere") String description, @NotNull(message = "Orice joc trebuie sa fie inglobat intr-o categorie.") GameType gameType) {
        this.name = name;
        this.description = description;
        this.gameType = gameType;
    }

    public Game(){

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

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
