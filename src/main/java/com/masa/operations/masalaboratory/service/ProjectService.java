package com.masa.operations.masalaboratory.service;

import com.masa.operations.masalaboratory.model.ConcreteWorkOrder;
import com.masa.operations.masalaboratory.model.Project;
import com.masa.operations.masalaboratory.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id){
        return projectRepository.findById(id);
    }
}
