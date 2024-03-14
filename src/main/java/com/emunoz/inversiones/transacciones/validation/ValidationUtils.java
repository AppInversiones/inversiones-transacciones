package com.emunoz.inversiones.transacciones.validation;

import com.emunoz.inversiones.transacciones.models.response.TransactionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationUtils {

    public ResponseEntity<TransactionResponseDTO> handleValidationErrors(BindingResult bindingResult) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }

            for (ObjectError error : bindingResult.getGlobalErrors()) {
                errors.add(error.getDefaultMessage());
            }
            transactionResponseDTO.setMessage("Campos vacios");
            transactionResponseDTO.setData(errors);
            transactionResponseDTO.setCode(0);
            return new ResponseEntity<>(transactionResponseDTO, HttpStatus.BAD_REQUEST);
        }

        return null; // No hay errores de validación
    }

}
