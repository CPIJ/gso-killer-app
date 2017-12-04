package shared.event.handler;

import shared.event.WebSocketEventHandler;

public class OnUserRegisteredEvent extends WebSocketEventHandler {

    @Override
    public void handle(String eventName, Object... data) {
        template.convertAndSend("/topic/after-registration", data);
    }
}
