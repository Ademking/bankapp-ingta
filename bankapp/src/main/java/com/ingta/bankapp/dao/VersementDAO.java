package com.ingta.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingta.bankapp.entity.Versement;

public interface VersementDAO extends JpaRepository<Versement, Long> {

}
