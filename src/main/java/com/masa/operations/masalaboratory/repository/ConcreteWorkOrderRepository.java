package com.masa.operations.masalaboratory.repository;

import com.masa.operations.masalaboratory.model.ConcreteWorkOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcreteWorkOrderRepository extends CrudRepository<ConcreteWorkOrder, Long> {
}
