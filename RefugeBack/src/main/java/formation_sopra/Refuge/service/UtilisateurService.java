package formation_sopra.Refuge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;

@Service
public class UtilisateurService {

	@Autowired
	IDAOUtilisateur daoUtilisateur;
	
	public boolean existById(Integer id) {
		return daoUtilisateur.existsById(id);
	}

	public Utilisateur findById(Integer id)
	{
		Optional<Utilisateur> opt = daoUtilisateur.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Utilisateur> findAll()
	{
		return daoUtilisateur.findAll();
	}
	
	public List<Client> findAllClient()
	{
		return daoUtilisateur.findAllClient();
	}
	
	public List<Worker> findAllWorker()
	{
		return daoUtilisateur.findAllWorker();
	}
	
	public List<Admin> findAllAdmin()
	{
		return daoUtilisateur.findAllAdmin();
	}

	public Utilisateur create(Utilisateur utilisateur) 
	{
		utilisateur=daoUtilisateur.save(utilisateur);
		return utilisateur;
	}

	public Utilisateur update(Utilisateur utilisateur) 
	{
		utilisateur=daoUtilisateur.save(utilisateur);
		return utilisateur;
	}

	public boolean deleteById(Integer id) 
	{
		daoUtilisateur.deleteById(id);
		return true;
	}


}
