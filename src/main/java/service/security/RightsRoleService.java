package service.security;

import model.Right;
import model.Role;
import model.User;

import java.util.List;

public interface RightsRoleService {
    void addRole(String role);
    void addRight(String right);
    Role findRoleByTitle(String role);
    Role findRoleById(Long roleId);
    Right findRightByTitle(String right);
    void addRolesToUser(User user, List<Role> roles);
    List<Role> findRolesForUser(Long userId);
    void addRoleRight(Long roleId, Long rightId);

    public boolean hasEmployeeRole(User user);
}
