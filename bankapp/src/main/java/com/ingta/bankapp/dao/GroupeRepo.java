package com.ingta.bankapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingta.bankapp.entity.Groupe;

public interface GroupeRepo extends JpaRepository<Groupe, Long>{
	public List<Groupe> findAllByOrderByCodeGroupeDesc();
}
