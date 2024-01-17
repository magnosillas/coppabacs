package br.edu.ufape.lmts.sementes.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ufape.lmts.sementes.controller.dto.request.EnderecoRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.EnderecoResponse;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.model.Endereco;
import br.edu.ufape.lmts.sementes.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class EnderecoController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("endereco")
	public List<EnderecoResponse> getAllEndereco() {
		return facade.getAllEndereco()
			.stream()
			.map(EnderecoResponse::new)
			.toList();
	}
	
	@PostMapping("endereco")
	public EnderecoResponse createEndereco(@Valid @RequestBody EnderecoRequest newObj) {
		return new EnderecoResponse(facade.saveEndereco(newObj.convertToEntity()));
	}
	
	@GetMapping("endereco/{id}")
	public EnderecoResponse getEnderecoById(@PathVariable Long id) {
		return new EnderecoResponse(facade.findEnderecoById(id));
	}
	
	@PatchMapping("endereco/{id}")
	public EnderecoResponse updateEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoRequest obj) {
		try {
			//Endereco o = obj.convertToEntity();
			Endereco oldObject = facade.findEnderecoById(id);

			TypeMap<EnderecoRequest, Endereco> typeMapper = modelMapper
													.typeMap(EnderecoRequest.class, Endereco.class)
													.addMappings(mapper -> mapper.skip(Endereco::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new EnderecoResponse(facade.updateEndereco(oldObject));
		} catch (RuntimeException e) {
			if (!(e instanceof ObjectNotFoundException))
				throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
			else
				throw e;
		}
		
	}
	
	@DeleteMapping("endereco/{id}")
	public String deleteEndereco(@PathVariable Long id) {
		try {
			facade.deleteEndereco(id);
			return "";
		} catch (RuntimeException e) {
			if (!(e instanceof ObjectNotFoundException))
				throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
			else
				throw e;
		}
		
	}
	

}
