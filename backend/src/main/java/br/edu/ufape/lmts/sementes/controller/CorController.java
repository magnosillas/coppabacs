package br.edu.ufape.lmts.sementes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.edu.ufape.lmts.sementes.model.Cor;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.controller.dto.request.CorRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.CorResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class CorController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("cor")
	public List<CorResponse> getAllCor() {
		return facade.getAllCor()
			.stream()
			.map(CorResponse::new)
			.toList();
	}
	
	@PostMapping("cor")
	public CorResponse createCor(@Valid @RequestBody CorRequest newObj) {
		return new CorResponse(facade.saveCor(newObj.convertToEntity()));
	}
	
	@GetMapping("cor/{id}")
	public CorResponse getCorById(@PathVariable Long id) {
		try {
			return new CorResponse(facade.findCorById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cor " + id + " not found.");
		}
	}
	
	@PatchMapping("cor/{id}")
	public CorResponse updateCor(@PathVariable Long id, @Valid @RequestBody CorRequest obj) {
		try {
			//Cor o = obj.convertToEntity();
			Cor oldObject = facade.findCorById(id);

			TypeMap<CorRequest, Cor> typeMapper = modelMapper
													.typeMap(CorRequest.class, Cor.class)
													.addMappings(mapper -> mapper.skip(Cor::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new CorResponse(facade.updateCor(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("cor/{id}")
	public String deleteCor(@PathVariable Long id) {
		try {
			facade.deleteCor(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}
