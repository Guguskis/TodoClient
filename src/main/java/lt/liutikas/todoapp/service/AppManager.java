package lt.liutikas.todoapp.service;


import lt.liutikas.todoapp.exceptions.DuplicateException;
import lt.liutikas.todoapp.exceptions.LoginFailedException;
import lt.liutikas.todoapp.exceptions.NotFoundException;
import lt.liutikas.todoapp.model.Company;
import lt.liutikas.todoapp.model.Person;
import lt.liutikas.todoapp.model.Project;
import lt.liutikas.todoapp.model.User;
import lt.liutikas.todoapp.repository.ConnectionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppManager {
    private User currentUser = null;
    private List<User> users = new ArrayList<>();
    private ConnectionManager connection = new ConnectionManager();

    public void register(Person person) throws DuplicateException {
        createUser(person);
    }

    public Company register(Company company) {
        try {
            createUser(company);
            return company;
        } catch (DuplicateException e) {
            return null;
        }
    }

    private void createUser(User user) throws DuplicateException {
        try {
            getUser(user.getUsername());
            throw new DuplicateException("Username " + user.getUsername() + " already exists");

        } catch (NotFoundException e) {
            users.add(user);
        }

    }

    public void login(String username, String password) throws LoginFailedException {
        Optional<User> user = connection.findByUsername(username);

        if (userWasNotFound(user)) {
            throw new LoginFailedException("User does not exist.");
        } else if (isValidated(username, password, user.get())) {
            currentUser = user.get();
        } else {
            throw new LoginFailedException("Credentials are incorrect.");
        }
    }

    private boolean userWasNotFound(Optional<User> user) {
        return user.isEmpty() || !user.get().isActive();
    }

    private boolean isValidated(String myUsername, String myPassword, User user) {
        return usernameMatch(myUsername, user.getUsername())
                && passwordMatch(myPassword, user.getPassword());
    }

    private boolean passwordMatch(String myPassword, String otherPassword) {
        return otherPassword.equals(myPassword);
    }

    private boolean usernameMatch(String myUsername, String otherUsername) {
        return otherUsername.equalsIgnoreCase(myUsername);
    }

    public void logout() {
        currentUser = null;
    }

    public boolean canRegister(String username) {
        try {
            getUser(username);
            return false;
        } catch (NotFoundException e) {
            return true;
        }
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getUser(int id) throws NotFoundException {
        for (var user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundException("User with id=" + id + " not found.");
    }

    public User getUser(String username) throws NotFoundException {
        for (var user : users) {
            if (usernameMatch(username, user)) {
                return user;
            }
        }
        throw new NotFoundException("User with username=" + username + " not found.");
    }

    private boolean usernameMatch(String username, User user) {
        return user.getUsername().equalsIgnoreCase(username);
    }

    public List<Project> getProjects() {
        return currentUser.getProjects();
    }

    public Project getProject(String projectName) throws NotFoundException {
        return currentUser.getProject(projectName);
    }

    public void createProject(Project project) throws DuplicateException {
        try {
            getProject(project.getName());
            throw new DuplicateException("Project \"" + project.getName() + "\" already exists.");
        } catch (NotFoundException e) {
            currentUser.createProject(project);
        }
    }

    public void removeProject(Project projectToRemove) {
        if (!projectToRemove.isOwner(currentUser)) {
            return;
        }

        var projects = currentUser.getProjects();
        var filteredList = projects.stream()
                .filter(project -> project.equals(projectToRemove))
                .findFirst();
        if (filteredList == null) {
            return;
        }

        var projectMembers = projectToRemove.getMembers();
        projectMembers.stream()
                .forEach(member -> member.removeProject(projectToRemove));

        projects.removeIf(project -> project.equals(projectToRemove));
    }

    public User getCurrentUser() {
        return currentUser;
    }

}
