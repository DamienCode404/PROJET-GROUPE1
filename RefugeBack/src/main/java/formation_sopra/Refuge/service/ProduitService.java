package formation_sopra.Refuge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation_sopra.Refuge.dao.IDAOProduit;
import formation_sopra.Refuge.model.Produit;

@Service
public class ProduitService {

	@Autowired
	IDAOProduit daoProduit;
	
	public boolean existById(Integer id) {
		return daoProduit.existsById(id);
	}

	public Produit findById(Integer id)
	{
		Optional<Produit> opt = daoProduit.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Produit> findAll()
	{
		return daoProduit.findAll();
	}

	public Produit create(Produit produit) 
	{
		produit=daoProduit.save(produit);
		return produit;
	}

	public Produit update(Produit produit) 
	{
		produit=daoProduit.save(produit);
		return produit;
	}

	public boolean deleteById(Integer id) 
	{
		daoProduit.deleteById(id);
		return true;
	}


}
