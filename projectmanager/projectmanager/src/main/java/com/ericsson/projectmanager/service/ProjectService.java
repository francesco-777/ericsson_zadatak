package com.ericsson.projectmanager.service;


import com.ericsson.projectmanager.exeption.UserNotFoundException;
import com.ericsson.projectmanager.model.Project;
import com.ericsson.projectmanager.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Project addProject(Project project) {
        project.setProjectCode(UUID.randomUUID().toString());
        return projectRepo.save(project);
    }

    public List<Project> findAllProjects() {
        return projectRepo.findAll();
    }

    public Project updateProject(Project project) {
        return projectRepo.save(project);
    }

    public Project findProjectById(Long id) {
        return projectRepo.findProjectById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteProject(Long id) {
        projectRepo.deleteProjectById(id);
    }

}
