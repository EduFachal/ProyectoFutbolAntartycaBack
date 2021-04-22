package com.antartyca.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.antartyca.proyecto.model.EquipoModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Repository
public interface EquipoRepository extends JpaRepository <EquipoModel,Integer>{

    @Query(value = "SELECT next_val FROM equipo_seq", nativeQuery = 
    true)
    Integer getNextSeriesId();
}
