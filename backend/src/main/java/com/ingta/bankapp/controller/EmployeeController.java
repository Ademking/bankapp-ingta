package com.ingta.bankapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingta.bankapp.dao.EmployeeDAO;
import com.ingta.bankapp.dao.GroupeRepo;
import com.ingta.bankapp.entity.Employes;
import com.ingta.bankapp.entity.Groupe;

@RestController
class EmployeeController {

  private final EmployeeDAO repository;
  public GroupeRepo groupeRepo;

  EmployeeController(EmployeeDAO repository, GroupeRepo groupeRepo) {
    this.repository = repository;
    this.groupeRepo = groupeRepo;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  List<Employes> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]
  @CrossOrigin()
  @PostMapping("/employees/{groupID}")
  Employes newEmp(@RequestBody Employes newEmp, @PathVariable String groupID) {
	  
	  	Employes emp1 = new Employes(newEmp.getNomEmploye());
		Optional<Groupe> gps = groupeRepo.findById(Long.parseLong(groupID));
		Collection<Groupe> gCollection = new ArrayList<Groupe>();
		gCollection.add(gps.get());
		emp1.setGroupes(gCollection);
		
    return repository.save(emp1);
  }

  // Single item
  @GetMapping("/employees/{id}/{groupId}")
  Employes one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("No Employee found"));
  }

  @PutMapping("/employees/{id}/{groupId}")
  Employes replaceEmp(@RequestBody Employes NewEmp, @PathVariable Long id, @PathVariable String groupId) {
	  
    return repository.findById(id)
      .map(e -> {
    	  e.setNomEmploye(NewEmp.getNomEmploye());
    	  Collection<Groupe> gCollection = new ArrayList<Groupe>();
    	  Optional<Groupe> gps = groupeRepo.findById(Long.parseLong(groupId));
    	  gCollection.add(gps.get());
    	  e.setGroupes(gCollection);
          return repository.save(e);
      })
      .orElseThrow();
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmp(@PathVariable Long id) {
    repository.deleteById(id);
  }
}