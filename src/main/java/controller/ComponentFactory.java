package controller;

import database.FactoryDataBase;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.cont.ContRepository;
import repository.cont.ContRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.cont.ContService;
import service.cont.ContServiceImpl;
import service.security.RightsRoleService;
import service.security.RightsRoleServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

import java.sql.Connection;


public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final ContService contService;
    private final ClientService clientService;
    private final RightsRoleService rightsRoleService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ContRepository contRepository;
    private final ClientRepository clientRepository;


    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new FactoryDataBase().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.rightsRoleService= new RightsRoleServiceImpl(rightsRolesRepository);

        this.contRepository=new ContRepositoryMySQL(connection);
        this.contService=new ContServiceImpl(contRepository);

        this.clientRepository=new ClientRepositoryMySQL(connection);
        this.clientService=new ClientServiceImpl(clientRepository);
    }

    public RightsRoleService getRightsRoleService() {
        return rightsRoleService;
    }
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
    public ClientService getClientService() {
        return clientService;
    }
    public ContService getContService() {
        return contService;
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }
    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
}
