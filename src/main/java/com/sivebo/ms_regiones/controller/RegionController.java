package com.sivebo.ms_regiones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivebo.ms_regiones.dto.RegionResponseDTO;
import com.sivebo.ms_regiones.service.RegionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;

import com.sivebo.ms_regiones.dto.RegionRequestDTO;


@Slf4j
@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {

        private final RegionService regionService;

        @GetMapping
        public List<RegionResponseDTO> getAll(){
                return regionService.getAllRegiones();
        }

        @GetMapping("{id}")
        public ResponseEntity<RegionResponseDTO> getById(@PathVariable Long id) {
                return regionService.getById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/search")
        public ResponseEntity<RegionResponseDTO> getByAtribute(@RequestParam String nombre){
                log.info(">>> Buscando region por nombre: {}", nombre);
                return regionService.getByNombre(nombre)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<RegionResponseDTO> create(@Valid @RequestBody RegionRequestDTO dto) {
                return ResponseEntity.status(HttpStatus.CREATED).body(regionService.create(dto));
        }

        @PutMapping("/{id}")
        public ResponseEntity<RegionResponseDTO> update(
                @PathVariable Long id,
                @Valid @RequestBody RegionRequestDTO dto) {
                
                        return regionService.update(id, dto)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable Long id) {
                if (regionService.delete(id)) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("region eliminada");
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("region no encontrada o no se pudo eliminar");
                }
        }        
}
