package br.edu.ufape.lmts.sementes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.lmts.sementes.repository.AdminRepository;
import br.edu.ufape.lmts.sementes.model.Coppabacs;

@Service
public class AdminService implements AdminServiceInterface {
	@Autowired
	private AdminRepository repository;


	public Coppabacs saveAdmin(Coppabacs newInstance) {
		return repository.save(newInstance);
	}

	public Coppabacs updateAdmin(Coppabacs transientObject) {
		return repository.save(transientObject);
	}

	public Coppabacs findAdminById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Admin with id = " + id));
	}

	public List<Coppabacs> getAllAdmin(){
		return repository.findAll();
	}

	public void deleteAdmin(Coppabacs persistentObject){
		this.deleteAdmin(persistentObject.getId());
		
	}
	
	public void deleteAdmin(long id){
		Coppabacs obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Admin with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}