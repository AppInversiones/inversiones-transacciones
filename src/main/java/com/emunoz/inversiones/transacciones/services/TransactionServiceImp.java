package com.emunoz.inversiones.transacciones.services;

import com.emunoz.inversiones.transacciones.models.entity.TransactionEntity;
import com.emunoz.inversiones.transacciones.models.response.TransactionDataResponseDTO;
import com.emunoz.inversiones.transacciones.models.response.TransactionResponseDTO;
import com.emunoz.inversiones.transacciones.repository.TransactionRepository;
import com.emunoz.inversiones.transacciones.transactionMapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponseDTO getAllTransaction() {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        List<TransactionDataResponseDTO> transactionDataResponseDTOS = transactionEntities.stream()
                .map(TransactionMapper::toResponseDTO)
                .collect(Collectors.toList());

        if (transactionEntities.isEmpty()) {
            transactionResponseDTO.setMessage("No hay transacciones registradas.");
            transactionResponseDTO.setCode(1);
            return transactionResponseDTO;
        }

        transactionResponseDTO.setMessage("Billeteras encontradas.");
        transactionResponseDTO.setData(transactionDataResponseDTOS);
        transactionResponseDTO.setCode(2);

        return transactionResponseDTO;
    }
}
