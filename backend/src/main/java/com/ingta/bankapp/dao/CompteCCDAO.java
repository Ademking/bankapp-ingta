package com.ingta.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingta.bankapp.entity.CompteCC;

public interface CompteCCDAO extends JpaRepository<CompteCC, Long> {

}
