package com.ingta.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingta.bankapp.entity.Retrait;

public interface RetraitDAO extends JpaRepository<Retrait, Long> {

}
