import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<String, List<EventListener>> ascultatori = new HashMap<>();

    public void notify(String eventType, File file) {
        List<EventListener> users = ascultatori.get(eventType);
        if(users !=null)
            for (EventListener listener : users) {
                listener.update(eventType, file);
            }
    }

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.ascultatori.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = ascultatori.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = ascultatori.get(eventType);
        users.remove(listener);
    }


}