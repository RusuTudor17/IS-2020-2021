package RusuTudor.demo.service;

import RusuTudor.demo.models.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Integer findUserByGivenEmail(String email);
    Integer findUserByGivenEmailAndPassword(String email, String password);
    void updatePasswordForGivenEmail(String password, Integer id);
    Optional<User> findById(int id);
    List<User> findAll();
    void deleteById(int id);
    void save(User user);

}
