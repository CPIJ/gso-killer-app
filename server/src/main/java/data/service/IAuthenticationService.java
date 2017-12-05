package data.service;


import data.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAuthenticationService extends Remote {
    User login(String username, String password) throws RemoteException;
    boolean Register(User user) throws RemoteException;
    List<User> getAllUsers() throws RemoteException;
    boolean isAuthorized(User user) throws RemoteException;
}
