import database.FactoryDataBase;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.cont.ContRepository;
import repository.cont.ContRepositoryMySQL;
import repository.report.ReportRepository;
import repository.report.ReportRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.cont.ContService;
import service.cont.ContServiceImpl;
import service.report.ReportService;
import service.report.ReportServiceImpl;
import service.security.RightsRoleService;
import service.security.RightsRoleServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

import java.sql.Connection;


public class ComponentFactory {

    private final UserService userService;
    private final ContService contService;
    private final ClientService clientService;
    private final RightsRoleService rightsRoleService;
    private final ReportService reportService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ContRepository contRepository;
    private final ClientRepository clientRepository;
    private final ReportRepository reportRepository;

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
        this.userService = new UserServiceImpl(this.userRepository, this.rightsRolesRepository);
        this.rightsRoleService= new RightsRoleServiceImpl(rightsRolesRepository);

        this.reportRepository=new ReportRepositoryMySQL(connection);
        this.reportService=new ReportServiceImpl(reportRepository);

        this.contRepository=new ContRepositoryMySQL(connection);
        this.clientRepository=new ClientRepositoryMySQL(connection);

        this.contService=new ContServiceImpl(contRepository,this.clientRepository);


        this.clientService=new ClientServiceImpl(clientRepository);
    }

    public RightsRoleService getRightsRoleService() {
        return rightsRoleService;
    }
    public UserService getUserService() {
        return userService;
    }
    public ClientService getClientService() {
        return clientService;
    }
    public ContService getContService() {
        return contService;
    }
    public ReportService getReportService(){return reportService;}

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
}
