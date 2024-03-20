package com.emunoz.inversiones.transacciones.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.emunoz.inversiones.transacciones.models.entity.TransactionEntity;
import com.emunoz.inversiones.transacciones.models.response.TransactionResponseDTO;
import com.emunoz.inversiones.transacciones.repository.TransactionRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class TransactionServiceImpTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImp transactionService;



    @Test public void testGetAllTransaction_EmptyList() {

        when(transactionRepository.findAll()).thenReturn(Lists.newArrayList());
        TransactionResponseDTO result = transactionService.getAllTransaction();

        assertEquals("No hay transaciones registradas.", result.getMessage());
        assertEquals(1, result.getCode());
    }

    @Test
    public void testGetAllTransaction_NonEmptyList() {

        when(transactionRepository.findAll()).thenReturn(Lists.newArrayList(TransactionEntity.builder().build()));
        TransactionResponseDTO result = transactionService.getAllTransaction();

        assertEquals("Transaciones encontradas.", result.getMessage());
        assertEquals(2, result.getCode());

    }



}