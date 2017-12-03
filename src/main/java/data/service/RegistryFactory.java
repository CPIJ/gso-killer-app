package data.service;

import rmi.fontys.IRemotePublisherForListener;
import rmi.Config;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public final class RegistryFactory {

    private Registry registry;

    public RegistryFactory() {
        try {
            registry = LocateRegistry.getRegistry(Config.RMI_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public IRemotePublisherForListener getListener() {
        try {
            return (IRemotePublisherForListener) registry.lookup(Config.AUTHENTICATION_PUBLISHER);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
