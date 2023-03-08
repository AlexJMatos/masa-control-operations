package com.masa.operations.masalaboratory.service;

import com.masa.operations.masalaboratory.controller.view.WorkOrderController;
import com.masa.operations.masalaboratory.model.ConcreteWorkOrder;
import com.masa.operations.masalaboratory.repository.ConcreteWorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcreteWorkOrderService {
    @Autowired
    private ConcreteWorkOrderRepository repository;

    public ConcreteWorkOrder addConcreteWorkOrder(ConcreteWorkOrder concreteWorkOrder) {
        return repository.save(concreteWorkOrder);
    }

    public Iterable<ConcreteWorkOrder> getAllConcreteWorkOrders() {
        return repository.findAll();
    }

    public Optional<ConcreteWorkOrder> getById(Long id) {
        return repository.findById(id);
    }
}
