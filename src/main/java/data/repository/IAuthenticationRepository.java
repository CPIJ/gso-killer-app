package data.repository;

import data.model.User;

import java.util.List;

public interface IAuthenticationRepository {

    User login(String username, String password);
    boolean register(User user);
    List<User> getAllUsers();
    List<User> getPreviousUsers();
}
