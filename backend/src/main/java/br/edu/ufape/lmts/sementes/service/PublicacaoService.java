package br.edu.ufape.lmts.sementes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.lmts.sementes.repository.PublicacaoRepository;
import br.edu.ufape.lmts.sementes.model.Publicacao;

@Service
public class PublicacaoService implements PublicacaoServiceInterface {
	@Autowired
	private PublicacaoRepository repository;


	public Publicacao savePublicacao(Publicacao newInstance) {
		return repository.save(newInstance);
	}

	public Publicacao updatePublicacao(Publicacao transientObject) {
		return repository.save(transientObject);
	}

	public Publicacao findPublicacaoById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Publicacao with id = " + id));
	}

	public List<Publicacao> getAllPublicacao(){
		return repository.findAll();
	}

	public void deletePublicacao(Publicacao persistentObject){
		this.deletePublicacao(persistentObject.getId());
		
	}
	
	public void deletePublicacao(long id){
		Publicacao obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Publicacao with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}