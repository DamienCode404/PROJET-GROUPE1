package formation_sopra.Refuge.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur,Integer>{

	public Optional<Utilisateur> findByLogin(String login);
	public Utilisateur findByLoginAndPassword(String login,String password);
	@Query("from Admin")
	public List<Admin> findAllAdmin();
	@Query("from Client")
	public List<Client> findAllClient();
	@Query("from Worker")
	public List<Worker> findAllWorker();
//	@Query("SELECT s FROM Stagiaire s LEFT JOIN s.ordinateur o WHERE o IS NULL")
//	public List<Stagiaire> findAllStagiaireDisponibles();
}