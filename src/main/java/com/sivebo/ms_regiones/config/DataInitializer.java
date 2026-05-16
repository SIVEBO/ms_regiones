package com.sivebo.ms_regiones.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sivebo.ms_regiones.model.Region;
import com.sivebo.ms_regiones.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
        
        private final RegionRepository regionRepository;

        @Override
        public void run(String... args){
                if(regionRepository.count() > 0) {
                        log.info(">>> regiones ya cargadas. Se omite inicialización.");
                        return;
                }
                log.info(">>> Cargando regiones iniciales...");
                regionRepository.save(new Region(
                        null,
                        "Metropolitana"
                ));
                regionRepository.save(new Region(
                        null,
                        "Valparaiso"
                ));
                log.info(">>> Regiones iniciales cargadas exitosamente.");
        }
}
