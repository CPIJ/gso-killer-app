package data.repository;

import data.model.User;

import java.util.*;

public class MockAuthenticationRepository implements IAuthenticationRepository {

    private Set<User> users = new HashSet<>();
    private Set<User> oldUsers = new HashSet<>();

    public MockAuthenticationRepository() {

        User casper = new User();
        User admin = new User();

        casper.setUsername("casper");
        casper.setPassword("test");
        casper.setEmail("test");

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@admin.nl");

        users.addAll(Arrays.asList(
                admin, casper
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
