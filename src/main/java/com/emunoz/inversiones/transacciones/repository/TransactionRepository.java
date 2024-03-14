package com.emunoz.inversiones.transacciones.repository;

import com.emunoz.inversiones.transacciones.models.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
