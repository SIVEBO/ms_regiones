package com.sivebo.ms_regiones.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        
}
