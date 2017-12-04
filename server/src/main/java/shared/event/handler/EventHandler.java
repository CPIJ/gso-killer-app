package shared.event.handler;

public interface EventHandler {
    void handle(String eventName, Object... data);
}
