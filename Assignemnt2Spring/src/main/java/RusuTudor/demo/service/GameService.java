package RusuTudor.demo.service;

import RusuTudor.demo.models.Game;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface GameService {

    Optional<Game> findById(int id);
    List<Game> findAll();
    void deleteById(int id);
    void save(Game game);
    Integer findGameByGivenName(String name);
    void updateDescriptionForGivenId(String description,Integer id);
}
