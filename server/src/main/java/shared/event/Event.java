package shared.event;

import shared.event.handler.EventHandler;
import shared.util.Log;

import java.util.*;


public class Event {

    private static Map<String, List<EventHandler>> listeners = new HashMap<>();

    public static void listen(String name, EventHandler eventHandler) {

        Log.debug("Event", "Started listening for '" + name + "' with eventHandler: " + eventHandler);

        List<EventHandler> eventHandlers = listeners.get(name);

        if (eventHandlers == null) {
            listeners.put(name, Collections.singletonList(eventHandler));
        } else {
            eventHandlers.add(eventHandler);
        }
    }


    public static void emit(String name, Object... data) {

        Log.debug("Event", "Emitting '" + name + "' with data: " + Arrays.deepToString(data));

        listeners.forEach((listenerName, handlers) -> {
            if (listenerName.equals(name)) {
                handlers.forEach(handler -> handler.handle(name, data));
            }
        });
    }
}


