package web.api.controller;

import web.model.request.LoginRequest;
import web.model.request.SignUpRequest;
import web.model.response.LoginResponse;
import web.model.response.Response;
import web.model.response.SignUpResponse;
import rmi.fontys.IRemotePropertyListener;
import rmi.fontys.IRemotePublisherForListener;
import data.service.AuthenticationServiceFactory;
import data.service.IAuthenticationService;
import data.service.RegistryFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shared.event.Event;
import data.model.User;
import rmi.Config;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController extends UnicastRemoteObject implements IRemotePropertyListener {

    private IAuthenticationService authenticationService = AuthenticationServiceFactory.RMI();
    private IRemotePublisherForListener publisher = new RegistryFactory().getListener();

    protected AuthenticationController() throws RemoteException {
        publisher.subscribeRemoteListener(this, Config.AUTHENTICATION_NAME);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    public Response Login(@RequestBody LoginRequest loginRequest) {
        try {
            if (!loginRequest.isComplete()) {
                return LoginResponse.noUserFound();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            User user = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());

            if (user == null) {
                return LoginResponse.invalidCredentials();
            } else {
                return LoginResponse.success(user);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return LoginResponse.somethingWentWrong();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
    public Response signUp(@RequestBody SignUpRequest request) {
        try {
            if (!request.isComplete()) {
                return SignUpResponse.EmailAlreadyExists();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        User user = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getDate());

        try {
            if (authenticationService.Register(user)) {
                return SignUpResponse.Success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.somethingWentWrong();
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> getAllUsers() throws RemoteException {
        return authenticationService.getAllUsers();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        Event.emit("userRegistered", evt.getOldValue(), evt.getNewValue());
    }
}
