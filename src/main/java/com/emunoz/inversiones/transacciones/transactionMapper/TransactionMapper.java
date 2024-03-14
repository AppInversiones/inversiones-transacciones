package com.emunoz.inversiones.transacciones.transactionMapper;


import com.emunoz.inversiones.transacciones.models.entity.TransactionEntity;
import com.emunoz.inversiones.transacciones.models.request.TransactionRequestDTO;
import com.emunoz.inversiones.transacciones.models.response.TransactionDataResponseDTO;

public class TransactionMapper {

    public static TransactionEntity toEntity(TransactionRequestDTO transactionRequestDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setUsd_mount(transactionRequestDTO.getUsd_mount());
        transactionEntity.setClp_value(transactionRequestDTO.getClp_value());
        transactionEntity.setUser_id(transactionRequestDTO.getUser_id());

        return transactionEntity;
    }

    public static TransactionDataResponseDTO toResponseDTO(TransactionEntity transactionEntity) {
        TransactionDataResponseDTO transactiondataResponseDTO = new TransactionDataResponseDTO();
        transactiondataResponseDTO.setId(transactionEntity.getId());
        transactiondataResponseDTO.setUsd_mount(transactionEntity.getUsd_mount());
        transactiondataResponseDTO.setClp_value(transactionEntity.getClp_value());
        transactiondataResponseDTO.setTransaction_date(transactionEntity.getTransaction_date());

        return transactiondataResponseDTO;
    }
}
