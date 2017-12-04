package data.repository;

import org.joda.time.DateTime;
import data.model.User;

import java.util.*;

public class MockAuthenticationRepository implements IAuthenticationRepository {

    private Set<User> users = new HashSet<>();
    private Set<User> oldUsers = new HashSet<>();

    public MockAuthenticationRepository() {
        users.addAll(Arrays.asList(
                new User("admin", "admin", "admin@admin.nl", new DateTime("2012-06-08")),
                new User("casper", "test", "casper@syncoder.nl", new DateTime("2012-06-08"))
        ));
    }

    @Override
    public User login(String username, String password) {
        if (users.stream().anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password))) {
            return users
                    .stream()
                    .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                    .findFirst()
                    .get();
        }
        else {
            return null;
        }
    }

    @Override
    public boolean register(User user) {
        oldUsers = users;
        return !users.contains(user) && users.add(user);
    }

    @Override
    public List<User> getAllUsers() {

        return new ArrayList<>(users);
    }

    @Override
    public List<User> getPreviousUsers() {
        return new ArrayList<>(users);
    }
}
