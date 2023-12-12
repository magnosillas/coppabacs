package br.edu.ufape.lmts.sementes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.edu.ufape.lmts.sementes.model.Postavel;
import br.edu.ufape.lmts.sementes.facade.Facade;
import br.edu.ufape.lmts.sementes.controller.dto.request.PostavelRequest;
import br.edu.ufape.lmts.sementes.controller.dto.response.PostavelResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class PostavelController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("postavel")
	public List<PostavelResponse> getAllPostavel() {
		return facade.getAllPostavel()
			.stream()
			.map(PostavelResponse::new)
			.toList();
	}
	
	@PostMapping("postavel")
	public PostavelResponse createPostavel(@Valid @RequestBody PostavelRequest newObj) {
		return new PostavelResponse(facade.savePostavel(newObj.convertToEntity()));
	}
	
	@GetMapping("postavel/{id}")
	public PostavelResponse getPostavelById(@PathVariable Long id) {
		try {
			return new PostavelResponse(facade.findPostavelById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postavel " + id + " not found.");
		}
	}
	
	@PatchMapping("postavel/{id}")
	public PostavelResponse updatePostavel(@PathVariable Long id, @Valid @RequestBody PostavelRequest obj) {
		try {
			//Postavel o = obj.convertToEntity();
			Postavel oldObject = facade.findPostavelById(id);

			TypeMap<PostavelRequest, Postavel> typeMapper = modelMapper
													.typeMap(PostavelRequest.class, Postavel.class)
													.addMappings(mapper -> mapper.skip(Postavel::setId));			
			
			
			typeMapper.map(obj, oldObject);	
			return new PostavelResponse(facade.updatePostavel(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("postavel/{id}")
	public String deletePostavel(@PathVariable Long id) {
		try {
			facade.deletePostavel(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}