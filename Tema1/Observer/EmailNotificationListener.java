import java.io.File;

public class EmailNotificationListener implements EventListener {


    private String mail;


    @Override
    public void update(String eventType, File file) {
        System.out.println("Email catre " + mail + ": Cineva a efectuat operatia de ' " + eventType + " ' asupra fisierului: " + file.getName());
    }


    public EmailNotificationListener(String mail) {
        this.mail = mail;
    }




}