package com.antartyca.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.antartyca.proyecto.model.DepartamentoModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoModel, Integer>{
    @Query(value = "SELECT next_val FROM departamento_seq", nativeQuery = 
    true)
    Integer getNextSeriesId();
}
