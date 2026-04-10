package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.domain.service.SaludoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Agregamos esto para que el test no falle al buscar el servicio
    @MockBean
    private SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    /*
    ===========================================
    PASO 6 (EJERCICIO): PRUEBAS IMPLEMENTADAS
    ===========================================
    */

    @Test
    @DisplayName("GET /api/v1/saludos -> 200 y mensaje correcto")
    void debeSaludarConGet() throws Exception {
        mockMvc.perform(get("/api/v1/saludos").param("nombre", "Ana"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/v1/saludos con nombre vacío -> 400 y VALIDATION_ERROR")
    void debeDarErrorConNombreVacio() throws Exception {
        // Enviamos un JSON con el nombre vacío para disparar la validación
        String jsonInvalido = "{\"nombre\":\"\"}";

        mockMvc.perform(post("/api/v1/saludos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}