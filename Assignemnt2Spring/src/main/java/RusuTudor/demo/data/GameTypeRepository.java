package RusuTudor.demo.data;


import RusuTudor.demo.models.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface GameTypeRepository extends JpaRepository<GameType, Integer> {

    //@Query(value="SELECT id from game_type where game.name=:name",nativeQuery = true)
    Integer findGameTypesByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query (value="Update game_type set description=:description where game_type.id=:id",nativeQuery=true)
    void updateDescriptionForGivenId(@Param("description")String description,@Param("id") Integer id);

}
