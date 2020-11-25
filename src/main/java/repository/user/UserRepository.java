package repository.user;

import model.User;
import model.validation.Notification;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;

    User findByUsername(String username);
    boolean update(User user);
    boolean save(User user);
    boolean delete(String username);

    boolean findByEmail(String username);
    void removeAll();
}
