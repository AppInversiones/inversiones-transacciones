package com.emunoz.inversiones.transacciones.controllers;

import com.emunoz.inversiones.transacciones.models.response.TransactionResponseDTO;
import com.emunoz.inversiones.transacciones.services.TransactionService;
import com.emunoz.inversiones.transacciones.services.ValidationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private ValidationTokenService validationTokenService;

    private
    TransactionResponseDTO transactionResponse;

    private static final String AUTH_TOKEN = "valid-token";
    private static final String INVALID_AUTH_TOKEN = "invalid-token";

    @BeforeEach
    public void setUp() {

        when(validationTokenService.validateTokenAdmin(AUTH_TOKEN)).thenReturn(true);
        when(validationTokenService.validateTokenAdmin(INVALID_AUTH_TOKEN)).thenReturn(false);
    }

    @Test
    public void shouldReturnUnauthorizedWhenTokenIsInvalid() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/V1/transaction")
                        .header("Authorization", INVALID_AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Usuario no válido."))
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnNotFoundWhenTransactionsAreNotFound() throws Exception {
        TransactionResponseDTO transactionResponseDTO = TransactionResponseDTO.builder()
                .message("No hay transacciones registradas.")
                .code(1)
                .build();
        when(transactionService.getAllTransaction()).thenReturn(transactionResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/V1/transaction")
                        .header("Authorization", AUTH_TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("No hay transacciones registradas."))
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOkWhenTransactionsAreFound() throws Exception {
        TransactionResponseDTO transactionResponseDTO = TransactionResponseDTO.builder()
                .message("Billeteras encontradas.")
                .code(2)
                .build();
        when(transactionService.getAllTransaction()).thenReturn(transactionResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/V1/transaction")
                        .header("Authorization", AUTH_TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Billeteras encontradas."))
                .andExpect(jsonPath("$.code").value(2))
                .andExpect(status().isOk());
    }

}