package RusuTudor.demo;

import RusuTudor.demo.data.RoleRepository;
import RusuTudor.demo.models.Game;
import RusuTudor.demo.models.GameType;
import RusuTudor.demo.models.Role;
import RusuTudor.demo.models.User;
import RusuTudor.demo.service.GameService;
import RusuTudor.demo.service.GameTypeService;
import RusuTudor.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;


	@Autowired
	GameService gameService;

	@Autowired
	GameTypeService gameTypeService;


	@Test
	void user_test_CRUD() {
	//	User user= new User("darius@yahoo.com","parolaBuna",new Role());
		Role role = new Role("RoleExample");
		roleRepository.save(role);
		Assert.isTrue(roleRepository.findById(role.getId()) != null);


		User user = new User("darius@yahoo.com","parolaBuna",role);
		userService.save(user);
		System.out.println(userService.findUserByGivenEmail("darius@yahoo.com"));
		assert(userService.findUserByGivenEmail("darius@yahoo.com")!= null);

		userService.deleteById(user.getId());
		assert(userService.findUserByGivenEmail("darius@yahoo.com") == null);


		roleRepository.delete(role);
		System.out.println(roleRepository.findById(role.getId()));
		assert(roleRepository.findById(role.getId()).isPresent() == false);


	}


	@Test
	void game_test_CRUD() {
		GameType gameType =  new GameType("MMORPG","Massively multiplayer online role-playing game",null);
		gameTypeService.save(gameType);
		assert(gameTypeService.findById(gameType.getId()).isPresent() ==true);
		Game game = new Game("World of warcraft","Made by blizzard, fourth released game that is set in the Warcraft fantasy universe.",gameType);
		gameService.save(game);
		assert(gameService.findById(game.getId()).isPresent() == true);

		gameService.deleteById(game.getId());
		assert(gameService.findById(game.getId()).isPresent() != true);

		gameTypeService.deleteById(gameType.getId());
		assert(gameTypeService.findById(gameType.getId()).isPresent() !=true);




	}

}
