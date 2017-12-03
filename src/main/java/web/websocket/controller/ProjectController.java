package web.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import shared.event.Event;
import data.model.Project;
import web.model.client.ConnectedClient;
import web.model.client.DisconnectedClient;
import web.ProjectManager;

@Controller
public class ProjectController {

    private final SimpMessagingTemplate template;

    @Autowired
    public ProjectController(SimpMessagingTemplate template)
    {
        this.template = template;
    }

    @MessageMapping("/project/onClosed")
    public void onClosed(DisconnectedClient disconnectedClient) {
        Event.emit("onDisconnected", disconnectedClient);
    }

    @MessageMapping("/project/onOpened")
    public void onOpened(ConnectedClient connectedClient) {
        Event.emit("onConnected", connectedClient);
    }

    @MessageMapping("/project/change/{room}")
    public void onChange(Project project) {
        ProjectManager
                .get(project.getId())
                .setContent(project.getContent());

        template.convertAndSend("/topic/project/onchange/" + project.getId(), project);
    }
}
