package RusuTudor.demo.service;

import RusuTudor.demo.data.RoleRepository;
import RusuTudor.demo.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
