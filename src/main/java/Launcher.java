//Rusu Tudor, grupa 30234, semigrupa 2.
/*
Pentru a rula aplicatia, trebuie sa rulam boostrapul, si avand in vedere ca la inceput ne cere login-ul, adminul dupa rularea
bootstrapului, trebuie inserat in mod manual in baza de date. Adica, practic trebuie intr-un query de mysql, sa executam:

    use test_bank_db;
	insert into `user`  (username,`password`) values ("admin@yahoo.com","7DDDB0EE38FA7F35CB9F33D501C4B77C6088DE8535A2232183BEBDFE34073443");
	insert into user_role (user_id,role_id) values (1,1);
Astfel, vom avea un admin cu usernameul admin@yahoo.com si parola: parolaAdmin
 */

import controller.LoginController;
import model.validation.ClientValidator;
import model.validation.ContValidator;
import view.LoginView;


public class Launcher {

    public static void main(String[] args) {
        ContValidator cv= new ContValidator();
        ClientValidator clv=new ClientValidator();


        ComponentFactory componentFactory = ComponentFactory.instance(true);
        new LoginController(new LoginView(), componentFactory.getUserService(), componentFactory.getRightsRoleService(), componentFactory.getContService(),componentFactory.getClientService(),componentFactory.getReportService());
    }

}