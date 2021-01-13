package RusuTudor.demo.service;

import RusuTudor.demo.data.GameRepository;
import RusuTudor.demo.data.GameTypeRepository;
import RusuTudor.demo.models.GameType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameTypeServiceImpl implements GameTypeService{
    GameTypeRepository gameTypeRepository;

    public GameTypeServiceImpl(GameTypeRepository gameTypeRepository) {
        this.gameTypeRepository = gameTypeRepository;
    }

    @Override
    public Integer findGameTypesByName(String name) {
        return gameTypeRepository.findGameTypesByName(name);
    }

    @Override
    public List<GameType> findAll() {
        return gameTypeRepository.findAll();
    }

    @Override
    public void save(GameType gameType) {
        gameTypeRepository.save(gameType);
    }

    @Override
    public void deleteById(int id) {
        gameTypeRepository.deleteById(id);
    }

    @Override
    public Optional<GameType> findById(int id) {
        return gameTypeRepository.findById(id);
    }

    @Override
    public void updateDescriptionForGivenId(String description, Integer id) {
        gameTypeRepository.updateDescriptionForGivenId(description,id);
    }


}
