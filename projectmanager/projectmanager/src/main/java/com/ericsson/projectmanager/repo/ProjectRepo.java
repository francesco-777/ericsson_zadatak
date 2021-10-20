package com.ericsson.projectmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ericsson.projectmanager.model.Project;

import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    void deleteProjectById(Long id);

    Optional<Project> findProjectById(Long id);
}
