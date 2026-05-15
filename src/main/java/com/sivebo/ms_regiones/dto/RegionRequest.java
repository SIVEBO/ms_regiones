package com.sivebo.ms_regiones.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionRequest {
        
        @NotBlank(message = "El nombre de la región es obligatorio")
        private String nombre;
}
