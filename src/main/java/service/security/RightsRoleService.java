package service.security;

import model.Right;
import model.Role;
import model.User;

import java.util.List;

public interface RightsRoleService {


    boolean hasEmployeeRole(User user);
}
