package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record PrestamoResponse(
        BigDecimal cuotaMensual,
        BigDecimal interesTotal,
        BigDecimal totalPagar,
        Instant timestamp
) {
}