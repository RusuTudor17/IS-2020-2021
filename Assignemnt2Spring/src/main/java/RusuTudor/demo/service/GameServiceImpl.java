package RusuTudor.demo.service;

import RusuTudor.demo.data.GameRepository;
import RusuTudor.demo.models.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl  implements  GameService{
    GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);

    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        gameRepository.deleteById(id);
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Integer findGameByGivenName(String name) {
        return gameRepository.findGameByGivenName(name);
    }

    @Override
    public void updateDescriptionForGivenId(String description, Integer id) {
        gameRepository.updateDescriptionForGivenId(description,id);
    }
}
