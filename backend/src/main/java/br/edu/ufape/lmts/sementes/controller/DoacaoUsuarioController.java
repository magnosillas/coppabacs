package br.edu.ufape.lmts.sementes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.edu.ufape.lmts.sementes.model.DoacaoUsuario;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.controller.dto.request.DoacaoUsuarioRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.DoacaoUsuarioResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class DoacaoUsuarioController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("doacaoUsuario")
	public List<DoacaoUsuarioResponse> getAllDoacaoUsuario() {
		return facade.getAllDoacaoUsuario()
			.stream()
			.map(DoacaoUsuarioResponse::new)
			.toList();
	}
	
	@PostMapping("doacaoUsuario")
	public DoacaoUsuarioResponse createDoacaoUsuario(@Valid @RequestBody DoacaoUsuarioRequest newObj) {
		return new DoacaoUsuarioResponse(facade.saveDoacaoUsuario(newObj.convertToEntity()));
	}
	
	@GetMapping("doacaoUsuario/{id}")
	public DoacaoUsuarioResponse getDoacaoUsuarioById(@PathVariable Long id) {
		try {
			return new DoacaoUsuarioResponse(facade.findDoacaoUsuarioById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DoacaoUsuario " + id + " not found.");
		}
	}
	
	@PatchMapping("doacaoUsuario/{id}")
	public DoacaoUsuarioResponse updateDoacaoUsuario(@PathVariable Long id, @Valid @RequestBody DoacaoUsuarioRequest obj) {
		try {
			//DoacaoUsuario o = obj.convertToEntity();
			DoacaoUsuario oldObject = facade.findDoacaoUsuarioById(id);

			TypeMap<DoacaoUsuarioRequest, DoacaoUsuario> typeMapper = modelMapper
													.typeMap(DoacaoUsuarioRequest.class, DoacaoUsuario.class)
													.addMappings(mapper -> mapper.skip(DoacaoUsuario::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new DoacaoUsuarioResponse(facade.updateDoacaoUsuario(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("doacaoUsuario/{id}")
	public String deleteDoacaoUsuario(@PathVariable Long id) {
		try {
			facade.deleteDoacaoUsuario(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}