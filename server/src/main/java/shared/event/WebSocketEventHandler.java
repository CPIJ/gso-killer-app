package shared.event;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import shared.event.handler.EventHandler;

public class WebSocketEventHandler implements EventHandler {

    protected SimpMessagingTemplate template;

    public WebSocketEventHandler using(SimpMessagingTemplate template) {
        this.template = template;
        return this;
    }

    @Override
    public void handle(String eventName, Object... data) {
        System.out.println("Please implement me!");
    }
}
