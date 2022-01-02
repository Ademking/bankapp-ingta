package com.ingta.bankapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingta.bankapp.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {
	public List<Client> findAllByOrderByCodeClientDesc();
}
