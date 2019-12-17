package lt.liutikas.todoapp.model;


import lt.liutikas.todoapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private boolean active = true;
    private List<Project> projects = new ArrayList<>();

    public User(int id, String username, String password, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Project getProject(String name) throws NotFoundException {
        for (var project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        throw new NotFoundException("User is not assigned to \"" + name + "\" project");
    }

    public void createProject(Project project) {
        project.setOwner(this);
        projects.add(project);
    }

    public void assignProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project projectToRemove) {
        projects.removeIf(project -> project.equals(projectToRemove));
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", active=" + active + '}';
    }

    @Override
    public boolean equals(Object user) {
        if (user == null) {
            return false;
        } else return ((User) user).getUsername().equals(username);
    }
}
