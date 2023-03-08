package com.masa.operations.masalaboratory.repository;

import com.masa.operations.masalaboratory.model.ConcreteWorkOrder;
import com.masa.operations.masalaboratory.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
