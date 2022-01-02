package com.ingta.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingta.bankapp.entity.Operation;

public interface OperationRepo extends JpaRepository<Operation, Long> {

}
