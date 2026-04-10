package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Service
public class PrestamoService {

    public PrestamoResponse simular(BigDecimal monto, BigDecimal tasaAnual, int meses) {
        // Validación de regla de negocio
        if (monto.compareTo(BigDecimal.valueOf(1000000)) > 0) {
            throw new IllegalArgumentException("El monto solicitado excede el límite permitido de 1,000,000");
        }

        double p = monto.doubleValue();
        double r = (tasaAnual.doubleValue() / 12) / 100;
        int n = meses;

        double factor = Math.pow(1 + r, n);
        double cuota = p * (r * factor) / (factor - 1);
        double totalPagar = cuota * n;
        double interesTotal = totalPagar - p;

        return new PrestamoResponse(
                BigDecimal.valueOf(cuota).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(interesTotal).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(totalPagar).setScale(2, RoundingMode.HALF_UP),
                Instant.now()
        );
    }
}