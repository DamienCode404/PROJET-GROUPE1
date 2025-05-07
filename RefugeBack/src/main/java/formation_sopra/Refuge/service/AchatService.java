package formation_sopra.Refuge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation_sopra.Refuge.dao.IDAOAchat;
import formation_sopra.Refuge.model.Achat;

@Service
public class AchatService {

	@Autowired
	IDAOAchat daoAchat;
	
	public boolean existById(Integer id) {
		return daoAchat.existsById(id);
	}

	public Achat findById(Integer id)
	{
		Optional<Achat> opt = daoAchat.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Achat> findAll()
	{
		return daoAchat.findAll();
	}

	public Achat create(Achat achat) 
	{
		achat=daoAchat.save(achat);
		return achat;
	}

	public Achat update(Achat achat) 
	{
		achat=daoAchat.save(achat);
		return achat;
	}

	public boolean deleteById(Integer id) 
	{
		daoAchat.deleteById(id);
		return true;
	}


}
