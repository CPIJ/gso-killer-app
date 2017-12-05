package data.service;

import rmi.fontys.RemotePublisher;
import data.repository.IAuthenticationRepository;
import data.repository.MockAuthenticationRepository;
import data.model.User;
import rmi.Config;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RmiAuthenticationService extends UnicastRemoteObject implements IAuthenticationService {

    private IAuthenticationRepository repository = new MockAuthenticationRepository();
    private RemotePublisher publisher;

    public RmiAuthenticationService() throws RemoteException {
        publisher = new RemotePublisher();
        publisher.registerProperty(Config.AUTHENTICATION_NAME);
        Registry registry = LocateRegistry.getRegistry(Config.RMI_PORT);
        registry.rebind(Config.AUTHENTICATION_PUBLISHER, publisher);
    }

    @Override
    public User login(String username, String password) {
        return repository.login(username, password);
    }

    @Override
    public boolean Register(User user) throws RemoteException {
        if (repository.register(user)) {
            publisher.inform(Config.AUTHENTICATION_NAME, repository.getPreviousUsers(), repository.getAllUsers());
            return true;
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() throws RemoteException {
        return repository.getAllUsers();
    }

    @Override
    public boolean isAuthorized(User user) {
        return repository
                .getAllUsers()
                .stream()
                .anyMatch(u ->
                        u.getUsername().equals(user.getUsername())
                        && u.getPassword().equals(user.getPassword()));
    }
}
