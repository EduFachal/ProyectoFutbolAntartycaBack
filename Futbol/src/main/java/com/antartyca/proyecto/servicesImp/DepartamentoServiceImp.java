package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.DepartamentoModel;
import com.antartyca.proyecto.repository.DepartamentoRepository;
import com.antartyca.proyecto.services.DepartamentoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */


@Service
public class DepartamentoServiceImp implements DepartamentoService{

	@Autowired
	DepartamentoRepository departamentoRepo;
	
	@Override
	public DepartamentoModel saveDepartment(DepartamentoModel depart) {
		DepartamentoModel result = new DepartamentoModel();
		try {
			depart.setCod_depart(departamentoRepo.getNextSeriesId());
			result = departamentoRepo.save(depart);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DepartamentoModel> getAllDepartments() {
		List<DepartamentoModel> result = new ArrayList<>();
		try {
			result = departamentoRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public DepartamentoModel getById(Integer id) {
		DepartamentoModel result = new DepartamentoModel();
		try {
			result = departamentoRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DepartamentoModel> getByFilter(DepartamentoModel filter) {
		List<DepartamentoModel> result = new ArrayList<>();
		try {
			Example<DepartamentoModel> exmple = Example.of(filter);
			result = departamentoRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	

	/* 	Se recoge a través de un JSON la información de una departamento, primero se comprueba
 	si existe la departamento o no.
	En caso de existir buscará el departamento cuya id coincida y modificara los campos de nombre, descripcion,...
	En caso contrario mostrará por la consola un mensaje de equipo no encontrado
	Además en caso de que se modificaran empleados, se comprobaria previamente si tuviera informacion
	Y en caso de modificar la federacion comprobara si es nula o no*/

	@Override
	public DepartamentoModel updateDepartment(DepartamentoModel departamento) {
		DepartamentoModel result = new DepartamentoModel();
		try {
			if(departamentoRepo.existsById(departamento.getCod_depart())) {
				result = departamentoRepo.findById(departamento.getCod_depart()).get();
				if(departamento.getNombre()!=null) {
					result.setNombre(departamento.getNombre());
				}
				if(departamento.getDescripcion()!=null) {
					result.setDescripcion(departamento.getDescripcion());
					}
				if(departamento.getEmpleados().size()>0) {
					result.setEmpleados(departamento.getEmpleados());	
				}
				if(departamento.getFederacion()!=null) {
					result.setFederacion(departamento.getFederacion());	
				}
				result = departamentoRepo.save(result);
			}else {
				System.out.println("No existe el departamento indicado");
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
				departamentoRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
