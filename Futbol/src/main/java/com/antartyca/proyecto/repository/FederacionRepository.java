package com.antartyca.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antartyca.proyecto.model.FederacionModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Repository
public interface FederacionRepository extends JpaRepository<FederacionModel,Integer>{

}
