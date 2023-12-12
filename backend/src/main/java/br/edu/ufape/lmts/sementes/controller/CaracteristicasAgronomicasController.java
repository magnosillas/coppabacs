package br.edu.ufape.lmts.sementes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.edu.ufape.lmts.sementes.model.CaracteristicasAgronomicas;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.controller.dto.request.CaracteristicasAgronomicasRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.CaracteristicasAgronomicasResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class CaracteristicasAgronomicasController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("caracteristicasAgronomicas")
	public List<CaracteristicasAgronomicasResponse> getAllCaracteristicasAgronomicas() {
		return facade.getAllCaracteristicasAgronomicas()
			.stream()
			.map(CaracteristicasAgronomicasResponse::new)
			.toList();
	}
	
	@PostMapping("caracteristicasAgronomicas")
	public CaracteristicasAgronomicasResponse createCaracteristicasAgronomicas(@Valid @RequestBody CaracteristicasAgronomicasRequest newObj) {
		return new CaracteristicasAgronomicasResponse(facade.saveCaracteristicasAgronomicas(newObj.convertToEntity()));
	}
	
	@GetMapping("caracteristicasAgronomicas/{id}")
	public CaracteristicasAgronomicasResponse getCaracteristicasAgronomicasById(@PathVariable Long id) {
		try {
			return new CaracteristicasAgronomicasResponse(facade.findCaracteristicasAgronomicasById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CaracteristicasAgronomicas " + id + " not found.");
		}
	}
	
	@PatchMapping("caracteristicasAgronomicas/{id}")
	public CaracteristicasAgronomicasResponse updateCaracteristicasAgronomicas(@PathVariable Long id, @Valid @RequestBody CaracteristicasAgronomicasRequest obj) {
		try {
			//CaracteristicasAgronomicas o = obj.convertToEntity();
			CaracteristicasAgronomicas oldObject = facade.findCaracteristicasAgronomicasById(id);

			TypeMap<CaracteristicasAgronomicasRequest, CaracteristicasAgronomicas> typeMapper = modelMapper
													.typeMap(CaracteristicasAgronomicasRequest.class, CaracteristicasAgronomicas.class)
													.addMappings(mapper -> mapper.skip(CaracteristicasAgronomicas::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new CaracteristicasAgronomicasResponse(facade.updateCaracteristicasAgronomicas(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("caracteristicasAgronomicas/{id}")
	public String deleteCaracteristicasAgronomicas(@PathVariable Long id) {
		try {
			facade.deleteCaracteristicasAgronomicas(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}