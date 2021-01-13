package RusuTudor.demo.service;

import RusuTudor.demo.models.Game;
import RusuTudor.demo.models.GameType;

import java.util.List;
import java.util.Optional;

public interface GameTypeService {
    Integer findGameTypesByName(String name);
    List<GameType> findAll();
    void save(GameType gameType);
    void deleteById(int id);
    Optional<GameType>  findById(int id);
    void updateDescriptionForGivenId(String description,Integer id);


}
