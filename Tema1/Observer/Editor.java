
import java.io.File;

public class Editor {
    public EventManager events;
    private File file;

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("deschide", file);
    }




    public void saveFile() throws Exception {
        if (this.file != null) {
            events.notify("salveaza", file);
        } else {
            throw new Exception("Deschide initial fisierul.");
        }
    }




    public Editor() {
        this.events = new EventManager("deschide", "salveaza");
    }
}