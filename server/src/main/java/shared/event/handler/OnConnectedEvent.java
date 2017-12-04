package shared.event.handler;

import data.repository.MysqlProjectRepository;
import data.service.IProjectService;
import data.service.ProjectService;
import web.model.client.Client;
import shared.event.Event;
import data.model.Project;
import web.ProjectManager;
import web.model.client.ConnectedClient;

public class OnConnectedEvent implements EventHandler {

    private IProjectService service = new ProjectService(new MysqlProjectRepository());

    @Override
    public void handle(String eventName, Object... data) {

        ConnectedClient connectedClient = (ConnectedClient) data[0];

        Project project = ProjectManager.get(connectedClient.getProjectId());

        Client client = new Client(connectedClient.getClientId(), connectedClient.getUsername());

        if (project != null) {
            /*
            Het project is momenteel geopend door iemand anders.
            De nieuwe client wordt toegevoegd aan het project.
             */
            project.addClient(client);
        } else {
            /*
            Het project is momenteel niet geopend door een andere client.
            Eerst wordt er geprobeerd het project op te halen uit de database.
            Mocht deze nog niet bestaan dan word er een nieuw project gemaakt.
             */
            project = service.find(connectedClient.getProjectId());

            if (project == null) {
                project = new Project(connectedClient.getProjectId());
            }

            /*
            De nieuwe client wordt toegevoegd aan het project en het project
            wordt aan de lijst 'projecten die momenteel bewerkt worden' toegevoegd.
             */
            project.addClient(client);
            ProjectManager.put(connectedClient.getProjectId(), project);
        }

        Event.emit("onClientCountChange", project);
    }
}
