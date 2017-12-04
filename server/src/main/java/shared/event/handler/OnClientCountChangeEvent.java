package shared.event.handler;

import web.model.client.Client;
import shared.event.WebSocketEventHandler;
import data.model.Project;

import java.util.ArrayList;
import java.util.List;

public class OnClientCountChangeEvent extends WebSocketEventHandler {

    @Override
    public void handle(String eventName, Object... data) {
        Project project = (Project) data[0];

        template.convertAndSend("/topic/project/onClientCountChange/" + project.getId(), new Object() {
            public final String id = project.getId();
            public final String content = project.getContent();
            public final List<Client> clients = new ArrayList<>(project.getClients().values());
        });
    }
}
