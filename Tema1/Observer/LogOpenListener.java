import java.io.File;

public class LogOpenListener implements EventListener {
    private File log;



    @Override
    public void update(String eventType, File file) {
        System.out.println("Salvat in: " + log + ": Cineva a efectuat operatia de: " + eventType + " asupra fisierului: " + file.getName());
    }




    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }
}