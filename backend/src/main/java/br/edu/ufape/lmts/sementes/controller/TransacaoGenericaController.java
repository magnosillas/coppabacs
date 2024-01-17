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

import br.edu.ufape.lmts.sementes.controller.dto.request.TransacaoGenericaRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.TransacaoGenericaResponse;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.model.TransacaoGenerica;
import br.edu.ufape.lmts.sementes.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class TransacaoGenericaController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("transacaoGenerica")
	public List<TransacaoGenericaResponse> getAllTransacaoGenerica() {
		return facade.getAllTransacaoGenerica()
			.stream()
			.map(TransacaoGenericaResponse::new)
			.toList();
	}
	
	@PostMapping("transacaoGenerica")
	public TransacaoGenericaResponse createTransacaoGenerica(@Valid @RequestBody TransacaoGenericaRequest newObj) {
		return new TransacaoGenericaResponse(facade.saveTransacaoGenerica(newObj.convertToEntity()));
	}
	
	@GetMapping("transacaoGenerica/{id}")
	public TransacaoGenericaResponse getTransacaoGenericaById(@PathVariable Long id) {
		return new TransacaoGenericaResponse(facade.findTransacaoGenericaById(id));
	}
	
	@PatchMapping("transacaoGenerica/{id}")
	public TransacaoGenericaResponse updateTransacaoGenerica(@PathVariable Long id, @Valid @RequestBody TransacaoGenericaRequest obj) {
		try {
			//TransacaoGenerica o = obj.convertToEntity();
			TransacaoGenerica oldObject = facade.findTransacaoGenericaById(id);

			TypeMap<TransacaoGenericaRequest, TransacaoGenerica> typeMapper = modelMapper
													.typeMap(TransacaoGenericaRequest.class, TransacaoGenerica.class)
													.addMappings(mapper -> mapper.skip(TransacaoGenerica::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new TransacaoGenericaResponse(facade.updateTransacaoGenerica(oldObject));
		} catch (RuntimeException e) {
			if (!(e instanceof ObjectNotFoundException))
				throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
			else
				throw e;
		}
		
	}
	
	@DeleteMapping("transacaoGenerica/{id}")
	public String deleteTransacaoGenerica(@PathVariable Long id) {
		try {
			facade.deleteTransacaoGenerica(id);
			return "";
		} catch (RuntimeException e) {
			if (!(e instanceof ObjectNotFoundException))
				throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
			else
				throw e;
		}
		
	}
	

}
