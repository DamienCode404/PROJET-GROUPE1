package formation_sopra.Refuge.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Client;

@RestController
@RequestMapping("/inscription")
public class InscriptionRestController {

    private final IDAOUtilisateur daoUtilisateur;

    public InscriptionRestController(IDAOUtilisateur daoUtilisateur) {
        this.daoUtilisateur = daoUtilisateur;
    }

    @PostMapping("")
    public Client inscrire(@RequestBody Client client) {
        return daoUtilisateur.save(client);
    }
}

