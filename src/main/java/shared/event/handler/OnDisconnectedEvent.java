package shared.event.handler;

import data.repository.MysqlProjectRepository;
import data.service.IProjectService;
import data.service.ProjectService;
import shared.event.Event;
import data.model.Project;
import web.model.client.DisconnectedClient;
import web.ProjectManager;

public class OnDisconnectedEvent implements EventHandler {

    private IProjectService service = new ProjectService(new MysqlProjectRepository());

    @Override
    public void handle(String eventName, Object... data) {
        DisconnectedClient disconnectedClient = (DisconnectedClient) data[0];

        Project project = ProjectManager.get(disconnectedClient.getProjectId());
        project.removeClient(disconnectedClient.getClientId());

        if (project.getClients().size() == 0) {

            ProjectManager.remove(project);

            service.save(project);
        }

        Event.emit("onClientCountChange", project);
    }
}
