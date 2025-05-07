package formation_sopra.Refuge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation_sopra.Refuge.dao.IDAOEspece;
import formation_sopra.Refuge.model.Espece;

@Service
public class EspeceService {

	@Autowired
	IDAOEspece daoEspece;
	
	public boolean existById(Integer id) {
		return daoEspece.existsById(id);
	}

	public Espece findById(Integer id)
	{
		Optional<Espece> opt = daoEspece.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Espece> findAll()
	{
		return daoEspece.findAll();
	}

	public Espece create(Espece espece) 
	{
		espece=daoEspece.save(espece);
		return espece;
	}

	public Espece update(Espece espece) 
	{
		espece=daoEspece.save(espece);
		return espece;
	}

	public boolean deleteById(Integer id) 
	{
		daoEspece.deleteById(id);
		return true;
	}


}
