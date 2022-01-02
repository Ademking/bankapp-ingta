package com.ingta.bankapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingta.bankapp.dao.ClientRepo;
import com.ingta.bankapp.entity.Client;

@RestController
class ClientController {

  private final ClientRepo repository;

  ClientController(ClientRepo repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/clients")
  List<Client> all() {
    return repository.findAllByOrderByCodeClientDesc();
  }
  // end::get-aggregate-root[]
  @CrossOrigin()
  @PostMapping("/clients")
  Client newClient(@RequestBody Client newClient) {
    return repository.save(newClient);
  }

  // Single item
  @GetMapping("/clients/{id}")
  Client one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("No Client found"));
  }

  @PutMapping("/clients/{id}")
  Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(c -> {
        c.setNomClient(newClient.getNomClient());
        return repository.save(c);
      })
      .orElseThrow();
  }

  @DeleteMapping("/clients/{id}")
  void deleteClient(@PathVariable Long id) {
    repository.deleteById(id);
  }
}