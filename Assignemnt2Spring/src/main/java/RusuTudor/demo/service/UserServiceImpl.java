package RusuTudor.demo.service;

import RusuTudor.demo.data.UserRepository;
import RusuTudor.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Integer findUserByGivenEmail(String email) {
        return userRepository.findUserByGivenEmail(email);
    }

    @Override
    public Integer findUserByGivenEmailAndPassword(String email, String password) {
        return userRepository.findUserByGivenEmailAndPassword(email,encodePassword(password));
    }

    @Override
    public void updatePasswordForGivenEmail(String password, Integer id) {
        userRepository.updatePasswordForGivenEmail(encodePassword(password),id);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
