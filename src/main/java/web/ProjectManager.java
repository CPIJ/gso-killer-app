package web;

import data.model.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectManager {

    private static Map<String, Project> liveProjects = new HashMap<>();

    public static void put(String key, Project value) {
        liveProjects.put(key, value);
    }

    public static Project get(String key) {
        return liveProjects.get(key);
    }

    public static boolean contains(String key) {
        return liveProjects.containsKey(key);
    }

    public static List<Project> getAll() {
        return new ArrayList<>(liveProjects.values());
    }

    public static void remove(Project project) {
        liveProjects.remove(project.getId());
    }
}
