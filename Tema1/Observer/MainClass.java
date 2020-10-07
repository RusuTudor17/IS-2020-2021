//codul de pe siteul trimis de dumneavostra, de la "code examples", cu mici modificari.
public class MainClass {
    public static void main(String[] args) {


        LogOpenListener l1= new LogOpenListener("/cale/fisier.txt");
        EmailNotificationListener e1=new EmailNotificationListener("tudor@yahoo.com");

        Editor editor = new Editor();

        editor.events.subscribe("deschide", l1);

        editor.events.subscribe("salveaza", e1);


        try {
            editor.openFile("test1.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        editor.events.unsubscribe("deschide",l1);


        editor.events.unsubscribe("salveaza",e1);

        try {
            editor.openFile("test2.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }





        editor.events.subscribe("deschide", l1);


        editor.events.subscribe("salveaza", e1);

        try {
            editor.openFile("test3.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
