package formation_sopra.Refuge;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import formation_sopra.Refuge.dao.IDAOAchat;
import formation_sopra.Refuge.model.Achat;
import formation_sopra.Refuge.rest.AchatRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



@WebMvcTest(AchatRestController.class) // lance uniquement le test du controleur
public class AchatRestControllerTest {

    @Autowired
    private MockMvc mockMvc; // permet de simuler les appels HTTP sans serveur

    @MockBean
    private IDAOAchat daoAchat; //simule le comportement du dao

    @Autowired
    private ObjectMapper objectMapper; // pour la conversion JSON

    private Achat achat;

    @BeforeEach 
    public void setUp() {
        achat = new Achat(5, 20.0, LocalDate.now());
        achat.setId(1);
    }

    @Test
    public void testGetAllAchat() throws Exception {
        when(daoAchat.findAll()).thenReturn(List.of(achat)); //retour de la liste achat

        mockMvc.perform(get("/achat")) //envoie de la requete
        		.andDo(print()) 
                .andExpect(status().isOk())//verification statut + contenu
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].qte").value(5))
                .andExpect(jsonPath("$[0].prix").value(20.0));
    }

    @Test
    public void testCreateAchat() throws Exception {
        when(daoAchat.save(any(Achat.class))).thenReturn(achat);//creation de la liste

        mockMvc.perform(post("/achat") 
                .contentType(MediaType.APPLICATION_JSON) //envoie de la requete
                .content(objectMapper.writeValueAsString(achat))) //conversion JSON
                .andExpect(status().isOk()) //verification statut + contenu
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.qte").value(5))
                .andExpect(jsonPath("$.prix").value(20.0));
    }
}