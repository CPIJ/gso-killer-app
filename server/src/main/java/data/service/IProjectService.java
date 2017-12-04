package data.service;

import data.model.Project;

public interface IProjectService {

    Project find(String id);
    boolean save(Project project);
    boolean delete(Project project);

}
