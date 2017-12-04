package data.service;

import data.model.Project;
import data.repository.IProjectRepository;

public class ProjectService implements IProjectService {

    private IProjectRepository repository;

    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project find(String id) {
        return repository.find(id);
    }

    @Override
    public boolean save(Project project) {
        return repository.save(project);
    }

    @Override
    public boolean delete(Project project) {
        return repository.delete(project);
    }
}
