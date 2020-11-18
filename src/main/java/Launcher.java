//Momentan, aceasta este o varianta de draft a proiectului, astfel am facut operatiile pentru client si cont, insa nu si pentru
//register la regular_user. Am inserat in MySQL ca sa testez codul, atat la user un employee cat si i-am acordat rolul de user.
//Codul pentru operatii nu este final, astfel mai trebuie lucrat la partea de comunicare cu utilizatorul in caz de date gresite/exceptii etc.
//Codul de la operatiile de cont si client, CRUD este functional insa nu este complet terminat, mai trebuie lucrat la partea de notificari si validare a datelor. Astfel, va fi modificat si codul de la aceste operatii in stadiul final al proiectului.
import controller.ComponentFactory;
import controller.LoginController;
import view.LoginView;


public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        new LoginController(new LoginView(), componentFactory.getAuthenticationService(), componentFactory.getRightsRoleService(), componentFactory);
    }

}