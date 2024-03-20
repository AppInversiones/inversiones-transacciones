package com.emunoz.inversiones.transacciones.controllers;

import com.emunoz.inversiones.transacciones.models.response.TransactionResponseDTO;
import com.emunoz.inversiones.transacciones.services.TransactionService;
import com.emunoz.inversiones.transacciones.services.ValidationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/V1/transaction")
@AllArgsConstructor
@Log4j2
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ValidationTokenService validationTokenService;


    @Operation(summary = "Servicio que lista las transacciones de todos los usuarios.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Transacciones encontrados.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
                    }),
                    @ApiResponse(responseCode = "204", description = "No se encontraron transacciones.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content),
            }
    )
    @GetMapping
    public ResponseEntity<TransactionResponseDTO> getAllTransaction(@RequestHeader(name = "Authorization") String token) {

        // Verifica que el token sea valido o que tenga permisos de administrador.
        if (!validationTokenService.validateTokenAdmin(token)) {
            TransactionResponseDTO transactionResponse = new TransactionResponseDTO();

            transactionResponse.setMessage("Usuario no válido.");
            transactionResponse.setCode(1);
            return new ResponseEntity<>(transactionResponse, HttpStatus.UNAUTHORIZED);
        }

        TransactionResponseDTO res = transactionService.getAllTransaction();

        if (res.getCode() == 1){
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } else if (res.getCode() == 2) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }


}
