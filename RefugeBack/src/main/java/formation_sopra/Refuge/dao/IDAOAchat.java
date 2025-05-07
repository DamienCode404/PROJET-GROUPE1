package formation_sopra.Refuge.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import formation_sopra.Refuge.model.Achat;

public interface IDAOAchat extends JpaRepository<Achat,Integer>{

	@Query("from Achat")
	public List<Achat> findAllAchat();
}
	