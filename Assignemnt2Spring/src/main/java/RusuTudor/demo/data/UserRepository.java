package RusuTudor.demo.data;

import RusuTudor.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value="SELECT id from user where user.email=:email and user.password=:password",nativeQuery = true)
    Integer findUserByGivenEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query(value="SELECT id from user where user.email=:email",nativeQuery = true)
    Integer findUserByGivenEmail(@Param("email") String email);


    @Transactional
    @Modifying
    @Query (value="Update user set password=:password where user.id=:id",nativeQuery=true)
    void updatePasswordForGivenEmail(@Param("password")String password,@Param("id") Integer id);



}
