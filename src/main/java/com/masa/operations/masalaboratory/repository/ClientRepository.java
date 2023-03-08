package com.masa.operations.masalaboratory.repository;

import com.masa.operations.masalaboratory.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,  Long> {

}
