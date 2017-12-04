package data.service;

import rmi.Config;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuthenticationServiceFactory {

    public static IAuthenticationService RMI() {
        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(Config.RMI_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (registry == null) throw new IllegalArgumentException();

        try {
            return (IAuthenticationService) registry.lookup(Config.AUTHENTICATION_NAME);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

