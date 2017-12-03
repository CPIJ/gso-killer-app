package data.model;

import web.model.client.Client;

import java.util.HashMap;
import java.util.Map;

public class Project {

    private String id;
    private String content;
    private Map<String, Client> clients;

    private static final int LENGTH = 8;

    public Project(String id) {
        this.id = id;
        clients = new HashMap<>();
    }

    public Project() {
        clients = new HashMap<>();
    }

    public void addClient(Client c) {
        clients.put(c.getId(), c);
    }

    public void removeClient(String clientId) {
        clients.remove(clientId);
    }

    public void removeClient(Client client) {
        clients.remove(client.getId());
    }

    //region getter & setter
    public String getId() {
        return id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Client> getClients() {
        return clients;
    }


    //endregion
}
