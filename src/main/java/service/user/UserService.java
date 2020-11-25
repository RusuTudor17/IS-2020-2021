package service.user;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;

public interface UserService {


    Notification<User> login(String username, String password) throws AuthenticationException;
    //----------------------------------------------------------------------------------
    boolean logout(User user);
    Notification<Boolean> register(String username, String password);

    Notification<Boolean> update(String username, String password);
    boolean delete(String username);
    User viewUserByUsername(String username);
    //altele
    boolean existaDupaEmail(String username);
}
