package web.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import shared.event.*;
import shared.event.handler.OnClientCountChangeEvent;
import shared.event.handler.OnConnectedEvent;
import shared.event.handler.OnDisconnectedEvent;
import shared.event.handler.OnUserRegisteredEvent;
import web.WebConfig;

@SpringBootApplication
public class WebSocketStartup {

    private static SimpMessagingTemplate template;

    @Autowired
    public WebSocketStartup(SimpMessagingTemplate template) {
        WebSocketStartup.template = template;
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(WebSocketStartup.class)
                .properties(WebConfig.OPTIONS.get("WebSocket"))
                .run(args);

        setupListeners();
    }

    private static void setupListeners() {
        Event.listen("onConnected", new OnConnectedEvent());
        Event.listen("onDisconnected", new OnDisconnectedEvent());
        Event.listen("userRegistered", new OnUserRegisteredEvent().using(template));
        Event.listen("onClientCountChange", new OnClientCountChangeEvent().using(template));
    }
}
