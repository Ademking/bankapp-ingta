package com.ingta.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingta.bankapp.entity.Employes;

public interface EmployeeDAO extends JpaRepository<Employes, Long> {

}
