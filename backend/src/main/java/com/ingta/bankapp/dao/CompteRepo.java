package com.ingta.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingta.bankapp.entity.Compte;

public interface CompteRepo extends JpaRepository<Compte, Long> {

}
