package rmi;

import data.service.RmiAuthenticationService;
import shared.util.Log;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class RmiStartup {

    private static Registry registry;

    public static void main(String[] args) {
        try {
            registry = LocateRegistry.createRegistry(Config.RMI_PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            registry.rebind(Config.AUTHENTICATION_NAME, new RmiAuthenticationService());

            Log.debug("RmiStartup", "RMI Service online at localhost: "
                    + Config.RMI_PORT + "\nWith name(s):"
                    + Arrays.toString(registry.list())
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

