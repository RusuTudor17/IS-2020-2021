package service.security;

import model.Right;
import model.Role;
import model.User;
import repository.security.RightsRolesRepository;

import java.util.List;


public class RightsRoleServiceImpl implements RightsRoleService{
    private final RightsRolesRepository repository;
    public RightsRoleServiceImpl(RightsRolesRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean hasEmployeeRole(User user)
    {
        List<Role> roles = user.getRoles();

        for(Role r:roles)
        {

            if(r.getRole().equals("regular_user"))
            {
                return true;
            }
        }
        return false;
    }

}
