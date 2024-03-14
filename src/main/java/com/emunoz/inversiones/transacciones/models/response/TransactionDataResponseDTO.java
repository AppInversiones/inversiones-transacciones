package com.emunoz.inversiones.transacciones.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDataResponseDTO {

    private Long id;
    private Float usd_mount;
    private Float clp_value;
    private Long user_id;
    private LocalDateTime transaction_date;

}
