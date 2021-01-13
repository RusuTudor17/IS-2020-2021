package RusuTudor.demo.service;

import RusuTudor.demo.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

}
