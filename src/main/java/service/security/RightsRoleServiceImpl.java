package service.security;

import model.Right;
import model.Role;
import model.User;
import repository.security.RightsRolesRepository;

import java.util.List;

import static database.Constants.Roles.REGULAR_USER;

public class RightsRoleServiceImpl implements RightsRoleService{
    private final RightsRolesRepository repository;
    public RightsRoleServiceImpl(RightsRolesRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean hasEmployeeRole(User user)
    {
        List<Role> roles = user.getRoles();
        System.out.println(roles.size());
        for(Role r:roles)
        {
            System.out.println(r.getRole());
            if(r.getRole().equals("regular_user"))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public void addRole(String role) {
        repository.addRole(role);
    }

    @Override
    public void addRight(String right) {
        repository.addRight(right);
    }

    @Override
    public Role findRoleByTitle(String role) {
        return repository.findRoleByTitle(role);
    }

    @Override
    public Role findRoleById(Long roleId) {
        return repository.findRoleById(roleId);
    }

    @Override
    public Right findRightByTitle(String right) {
        return repository.findRightByTitle(right);
    }

    @Override
    public void addRolesToUser(User user, List<Role> roles) {
        repository.addRolesToUser(user,roles);
    }

    @Override
    public List<Role> findRolesForUser(Long userId) {
        return repository.findRolesForUser(userId);
    }

    @Override
    public void addRoleRight(Long roleId, Long rightId) {
        repository.addRoleRight(roleId,rightId);
    }
}
