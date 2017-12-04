package data.repository;

import data.model.Project;

public interface IProjectRepository {

    Project find(String id);
    boolean save(Project project);
    boolean delete(Project project);

}
