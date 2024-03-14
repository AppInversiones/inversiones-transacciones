package com.emunoz.inversiones.transacciones.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    private Long id;

    @DecimalMin(value = "0.01", message = "El Monto no puede estar en blanco o en cero (0).")
    private Float usd_mount;

    private Float clp_value;

    private Long user_id;

    private LocalDateTime transaction_date;
}
