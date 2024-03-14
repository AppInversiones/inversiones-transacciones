package com.emunoz.inversiones.transacciones.controllers;

import com.emunoz.inversiones.transacciones.models.response.TransactionResponseDTO;
import com.emunoz.inversiones.transacciones.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/V1/transaction")
@AllArgsConstructor
@Log4j2
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<TransactionResponseDTO> getAllTransaction() {

        TransactionResponseDTO res = transactionService.getAllTransaction();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
