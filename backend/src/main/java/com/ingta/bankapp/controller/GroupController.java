package com.ingta.bankapp.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingta.bankapp.dao.GroupeRepo;
import com.ingta.bankapp.entity.Groupe;

@RestController
class GroupController {

  private final GroupeRepo repository;

  GroupController(GroupeRepo repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/groups")
  List<Groupe> all() {
    return repository.findAllByOrderByCodeGroupeDesc();
  }
  // end::get-aggregate-root[]
  @CrossOrigin()
  @PostMapping("/groups")
  Groupe newGroup(@RequestBody Groupe newGroup) {
    return repository.save(newGroup);
  }

  // Single item
  @GetMapping("/groups/{id}")
  Groupe one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("No Group found"));
  }

  @PutMapping("/groups/{id}")
  Groupe replaceGroup(@RequestBody Groupe newGroup, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(g -> {
        g.setNomGroupe(newGroup.getNomGroupe());
        return repository.save(g);
      })
      .orElseThrow();
  }

  @DeleteMapping("/groups/{id}")
  void deleteGroup(@PathVariable Long id) {
    repository.deleteById(id);
  }
}