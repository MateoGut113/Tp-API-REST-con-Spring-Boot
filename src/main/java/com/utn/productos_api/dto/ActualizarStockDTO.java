package com.utn.productos_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ActualizarStockDTO(
        @NotNull(message = "El stock no puede ser nulo")
        @DecimalMin(value = "0")
        Integer stock

) { }
