package com.masa.operations.masalaboratory.service;

import com.masa.operations.masalaboratory.model.Client;
import com.masa.operations.masalaboratory.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    public boolean idExists(Long id) {
        return clientRepository.existsById(id);
    }
}
