package web.websocket.controller;

import data.model.User;
import data.service.AuthenticationServiceFactory;
import data.service.IAuthenticationService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import web.model.response.SignUpResponse;

import java.rmi.RemoteException;
import java.util.List;

@Controller
public class AuthenticationController {

    private IAuthenticationService authenticationService = AuthenticationServiceFactory.RMI();

    @MessageMapping("user/after-registration")
    @SendTo("/topic/after-registration")
    public List<User> getAllUsers(SignUpResponse response) throws RemoteException {
        return authenticationService.getAllUsers();
    }
}
