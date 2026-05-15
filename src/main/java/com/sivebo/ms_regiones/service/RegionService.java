package com.sivebo.ms_regiones.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sivebo.ms_regiones.dto.RegionRequestDTO;
import com.sivebo.ms_regiones.dto.RegionResponseDTO;
import com.sivebo.ms_regiones.model.Region;
import com.sivebo.ms_regiones.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {

        private final RegionRepository regionRepository;

        private RegionResponseDTO mapToDTO(Region region) {
                return new RegionResponseDTO(
                        region.getId(), 
                        region.getNombre()
                );
        }

        public List<RegionResponseDTO> getAllRegiones() {
                return regionRepository.findAll()
                        .stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList()
                );
        }

        public Optional<RegionResponseDTO> getById(Long id) {
                return regionRepository.findById(id).map(this::mapToDTO);
        }

        public Optional<RegionResponseDTO> getByNombre(String nombre) {
                return regionRepository.findByNombre(nombre).map(this::mapToDTO);
        }
        
        public RegionResponseDTO create(RegionRequestDTO dto){
                return mapToDTO(regionRepository.save(
                        new Region(
                                null, 
                                dto.getNombre())
                ));
        }

        public Optional<RegionResponseDTO> update(Long id, RegionRequestDTO dto) {
                return regionRepository.findById(id).map(region -> {
                        region.setNombre(dto.getNombre());
                        return mapToDTO(regionRepository.save(region));
                });
        }

        public boolean delete(Long id) {
                regionRepository.deleteById(id);
                return !regionRepository.existsById(id);
        }
}
