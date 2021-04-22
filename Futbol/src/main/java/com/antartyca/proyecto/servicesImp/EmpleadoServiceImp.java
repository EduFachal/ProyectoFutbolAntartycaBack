package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.EmpleadoModel;
import com.antartyca.proyecto.repository.EmpleadoRepository;
import com.antartyca.proyecto.services.EmpleadoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Service
public class EmpleadoServiceImp implements EmpleadoService{

	@Autowired
	EmpleadoRepository empleadoRepo;
	
	@Override
	public EmpleadoModel saveEmployer(EmpleadoModel empleado) {
		EmpleadoModel result = new EmpleadoModel();
		try {
			result = empleadoRepo.save(empleado);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<EmpleadoModel> getAllEmployees() {
		List<EmpleadoModel> result = new ArrayList<>();
		try {
			result = empleadoRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public EmpleadoModel getById(Integer id) {
		EmpleadoModel result = new EmpleadoModel();
		try {
			result = empleadoRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<EmpleadoModel> getByFilter(EmpleadoModel filter) {
		List<EmpleadoModel> result = new ArrayList<>();
		try {
			Example<EmpleadoModel> exmple = Example.of(filter);
			result = empleadoRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/* 	Se recoge a través de un JSON la información de un empleado, primero se comprueba
 	si existe la departamento o no.
	En caso de existir buscará el departamento cuya id coincida y modificara los campos de nombre, direccion, dni y telefono
	En caso contrario mostrará por la consola un mensaje de equipo no encontrado
	Además en caso de que se modificaran su departamento, se comprobaria previamente si es nula o no*/	
	@Override
	public EmpleadoModel updateEmployer(EmpleadoModel empleado) {
		EmpleadoModel result = new EmpleadoModel();
		try {
            if(empleadoRepo.existsById(empleado.getCod_emp())) {
                result = empleadoRepo.findById(empleado.getCod_emp()).get();
            	if(empleado.getNombre()!=null) {
            		result.setNombre(empleado.getNombre());                
            	}
            	if(empleado.getDireccion()!=null) {
            		result.setDireccion(empleado.getDireccion());
            	}
            	if(empleado.getDni()!=null) {
            		result.setDni(empleado.getDni());
            	}
            	if(empleado.getTelefono()!=null) {
            		result.setTelefono(empleado.getTelefono());
            	}
                if(empleado.getDepartamento()!=null) {
                	result.setDepartamento(empleado.getDepartamento());	
                }

                result = empleadoRepo.save(result);
            }
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			if(id != null) {
				empleadoRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
