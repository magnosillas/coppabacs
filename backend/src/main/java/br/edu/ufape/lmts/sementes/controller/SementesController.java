package br.edu.ufape.lmts.sementes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.edu.ufape.lmts.sementes.model.Sementes;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.controller.dto.request.SementesRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.SementesResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class SementesController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("sementes")
	public List<SementesResponse> getAllSementes() {
		return facade.getAllSementes()
			.stream()
			.map(SementesResponse::new)
			.toList();
	}
	
	@PostMapping("sementes")
	public SementesResponse createSementes(@Valid @RequestBody SementesRequest newObj) {
		return new SementesResponse(facade.saveSementes(newObj.convertToEntity()));
	}
	
	@GetMapping("sementes/{id}")
	public SementesResponse getSementesById(@PathVariable Long id) {
		try {
			return new SementesResponse(facade.findSementesById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sementes " + id + " not found.");
		}
	}
	
	@PatchMapping("sementes/{id}")
	public SementesResponse updateSementes(@PathVariable Long id, @Valid @RequestBody SementesRequest obj) {
		try {
			//Sementes o = obj.convertToEntity();
			Sementes oldObject = facade.findSementesById(id);

			TypeMap<SementesRequest, Sementes> typeMapper = modelMapper
													.typeMap(SementesRequest.class, Sementes.class)
													.addMappings(mapper -> mapper.skip(Sementes::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new SementesResponse(facade.updateSementes(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("sementes/{id}")
	public String deleteSementes(@PathVariable Long id) {
		try {
			facade.deleteSementes(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}