package formation_sopra.Refuge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation_sopra.Refuge.dao.IDAOAnimal;
import formation_sopra.Refuge.model.Animal;

@Service
public class AnimalService {

	@Autowired
	IDAOAnimal daoAnimal;
	
	public boolean existById(Integer id) {
		return daoAnimal.existsById(id);
	}

	public Animal findById(Integer id)
	{
		Optional<Animal> opt = daoAnimal.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Animal> findAll()
	{
		return daoAnimal.findAll();
	}

	public Animal create(Animal animal) 
	{
		animal=daoAnimal.save(animal);
		return animal;
	}

	public Animal update(Animal animal) 
	{
		animal=daoAnimal.save(animal);
		return animal;
	}

	public boolean deleteById(Integer id) 
	{
		daoAnimal.deleteById(id);
		return true;
	}
	



}
