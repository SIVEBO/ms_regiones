package com.sivebo.ms_regiones.repository;

import java.util.Optional;

import javax.swing.plaf.synth.Region;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long>{

        Optional<Region> findByNombre(String nombre);
}
