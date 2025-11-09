package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoDTO (
        @NotNull(message = "El nombre no puede ser nulo")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @Size(max = 500, message = "El descripcion puede tener hasta 500 caracteres")
        String descripcion,

        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.01")
        Double precio,

        @NotNull(message = "El stock no puede ser nulo")
        @DecimalMin(value = "0")
        Integer stock,

        @NotNull(message = "La catogoria no puede ser nula")
        Categoria categoria

){ }
