package RusuTudor.demo.data;

import RusuTudor.demo.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GameRepository  extends JpaRepository<Game, Integer> {
    @Query(value="SELECT id from game where game.name=:name",nativeQuery = true)
    Integer findGameByGivenName(@Param("name") String name);


    @Transactional
    @Modifying
    @Query (value="Update game set description=:description where game.id=:id",nativeQuery=true)
    void updateDescriptionForGivenId(@Param("description")String description,@Param("id") Integer id);



}
