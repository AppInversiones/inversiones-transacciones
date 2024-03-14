package com.emunoz.inversiones.transacciones.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "usd_mount")
    private Float usd_mount;

    @Column(name = "clp_value")
    private Float clp_value;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "Transaction_date")
    private LocalDateTime transaction_date;

    @PrePersist
    protected void onCreate() {
        transaction_date = LocalDateTime.now();
    }
}
