package formation_sopra.Refuge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation_sopra.Refuge.model.Animal;

public interface IDAOAnimal extends JpaRepository<Animal,Integer>{

	@Query("from Animal")
	public List<Animal> findAllAnimal();
	


}
	