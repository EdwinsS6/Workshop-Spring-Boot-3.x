package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    /*
    PASO 4 (EJERCICIO):
    - Modifica esta logica para personalizar el formato del nombre.
    */
    String normalizarNombre(String nombre) {
        // Si el nombre es nulo o está vacío, devolvemos "Mundo" por defecto
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Mundo";
        }

        // 1. Quitamos espacios al inicio y al final
        String nombreLimpio = nombre.trim();

        // 2. Aplicamos formato: Primera Mayúscula y el resto minúsculas
        // Ejemplo: "eDwInS" -> "Edwins"
        return nombreLimpio.substring(0, 1).toUpperCase() + 
               nombreLimpio.substring(1).toLowerCase();
    }
}