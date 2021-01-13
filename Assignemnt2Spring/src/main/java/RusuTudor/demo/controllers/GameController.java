package RusuTudor.demo.controllers;

import RusuTudor.demo.models.Game;
import RusuTudor.demo.models.User;
import RusuTudor.demo.service.GameService;
import RusuTudor.demo.service.GameTypeService;
import RusuTudor.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameTypeService gameTypeService;
    @GetMapping("displayGames")
    public String displayAllGames(Model model){
        model.addAttribute("games", gameService.findAll());
        return "games/displayGames";
    }


    @GetMapping("createGame")
    public String displayCreateGame(Model model) {

        model.addAttribute(new Game());
        model.addAttribute("gamePrimit",new Object());
        model.addAttribute("gameTypes",gameTypeService.findAll());
        return "games/createGame";

    }

    @PostMapping("createGame")
    public String processCreateGame(@ModelAttribute @Valid Game game, Errors errors,
                                        Model model){
        if(model.getAttribute("gameTypes") == null){
            model.addAttribute("gameTypes",gameTypeService.findAll());
        }

        System.out.println("AICI" + errors.getErrorCount());
        if(errors.hasErrors()){
            model.addAttribute("gamePrimit",new Object());
            return "games/createGame";
        }
        else {
            if (gameService.findGameByGivenName(game.getName()) == null) {
                model.addAttribute("gamePrimit", new Object());
                gameService.save(game);
                return "redirect:displayGames";
            } else {
                model.addAttribute("gamePrimit", null);
                return "games/createGame";
            }
        }
    }

    @GetMapping("deleteGame")
    public String displayDeleteGame(Model model) {

        model.addAttribute("games",gameService.findAll());
        return "games/deleteGame";
    }


    @PostMapping("deleteGame")
    public String processDeleteGame(@RequestParam(required=false) Integer gameId){
        if(gameId!=null){
            gameService.deleteById(gameId);
            return "redirect:displayGames";
        }
        else{
            return "redirect:deleteGame";
        }

    }


    @GetMapping("updateGame")
    public String displayUpdateGame(Model model) {

        model.addAttribute("games",gameService.findAll());
        return "games/updateGame";
    }


    @GetMapping("changeDescription")
    public String displayChangeDescription(@RequestParam Integer gameId, Model model) {
        Optional<Game> result = gameService.findById(gameId);

        if(result.isPresent()) {

            model.addAttribute(result.get());
            model.addAttribute("gameTypes",gameTypeService.findAll());
        }
        return "games/changeDescription";
    }


    @PostMapping("changeDescription")
    public String processChangeDescription(@RequestParam Integer gameId, @Valid @ModelAttribute Game game, Errors errors){
        if(errors.hasErrors()){
            return "games/changeDescription";
        }
        else {
            gameService.updateDescriptionForGivenId(game.getDescription(), gameId);
            return "redirect:displayGames";
        }
    }

}
