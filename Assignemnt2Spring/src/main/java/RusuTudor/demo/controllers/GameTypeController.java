package RusuTudor.demo.controllers;

import RusuTudor.demo.models.Game;
import RusuTudor.demo.models.GameType;
import RusuTudor.demo.service.GameService;
import RusuTudor.demo.service.GameTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("gameTypes")
public class GameTypeController {

    @Autowired
    private GameTypeService gameTypeService;

    @GetMapping("displayGameTypes")
    public String displayAllGameTypes(Model model){
        model.addAttribute("gameTypes", gameTypeService.findAll());
        return "gameTypes/displayGameTypes";
    }

    @GetMapping("createGameType")
    public String displayCreateGameType(Model model) {

        model.addAttribute(new GameType());
        model.addAttribute("gameTypePrimit",new Object());
        return "gameTypes/createGameType";

    }

    @PostMapping("createGameType")
    public String processCreateGameType(@ModelAttribute @Valid GameType gameType, Errors errors,
                                    Model model){

        if(errors.hasErrors()){
            model.addAttribute("gameTypePrimit",new Object());
            return "gameTypes/createGameType";
        }
        else {
            if (gameTypeService.findGameTypesByName(gameType.getName()) == null) {
                model.addAttribute("gameTypePrimit", new Object());
                gameTypeService.save(gameType);
                return "redirect:displayGameTypes";
            } else {
                model.addAttribute("gameTypePrimit", null);
                return "gameTypes/createGameType";
            }
        }
    }



    @GetMapping("updateGameType")
    public String displayUpdateGameType(Model model) {

        model.addAttribute("gameTypes",gameTypeService.findAll());
        return "gameTypes/updateGameType";
    }

    @GetMapping("detail")
    public String displayGameTypeDetails(@RequestParam Integer gameTypeId,Model model){
        Optional<GameType> result = gameTypeService.findById(gameTypeId);

            GameType gameType = result.get();
            model.addAttribute("gameType",gameType);
            model.addAttribute("games",gameType.getGames());

        return "gameTypes/detail";
    }


    @GetMapping("changeDescription")
    public String displayChangeDescription(@RequestParam Integer gameTypeId, Model model) {

        Optional<GameType> result = gameTypeService.findById(gameTypeId);

        if(result.isPresent()) {
            model.addAttribute(result.get());
        }
        return "gameTypes/changeDescription";
    }


    @PostMapping("changeDescription")
    public String processChangeDescription(@RequestParam Integer gameTypeId, @Valid @ModelAttribute GameType gameType, Errors errors){
        if(errors.hasErrors()){
            return "gameTypes/changeDescription";
        }
        else {
            gameTypeService.updateDescriptionForGivenId(gameType.getDescription(), gameTypeId);
            return "redirect:displayGameTypes";
        }
    }


}
