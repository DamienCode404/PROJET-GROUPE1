package formation_sopra.Refuge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import formation_sopra.Refuge.rest.request.AnimalRequest;
@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {
	
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }
    
    @Test
    void okPourCreerUnAnimal() throws Exception {
    	// On créé un animal
    	AnimalRequest animalRequest = new AnimalRequest();
    	animalRequest.setNom("Rex");
    	animalRequest.setRace("Chien");
    	animalRequest.setDescription("Un chien fidèle");
    	
    	// On le covertit en Json
    	ObjectMapper objectMapper = new ObjectMapper();
    	String newAnimalJson = objectMapper.writeValueAsString(animalRequest);
    	
    	// On l'injecte
    	this.mockMvc
    	.perform(MockMvcRequestBuilders.post("/animal")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(newAnimalJson))
    	.andExpect(status().isOk())  
    	.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
    	.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Rex"))
    	.andExpect(MockMvcResultMatchers.jsonPath("$.race").value("Chien"))
    	.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Un chien fidèle"));
    }

    @Test
    public void okPourGetTousLesAnimaux() throws Exception {
        this.mockMvc
            .perform(get("/animal"))
            .andExpect(status().isOk());
    }

    
    @Test
    public void okPourGetUnAnimal() throws Exception {
        // Créer un animal
        AnimalRequest animalRequest = new AnimalRequest();
        animalRequest.setNom("Rex");
        animalRequest.setRace("Chien");
        animalRequest.setDescription("Un chien fidèle");

        ObjectMapper objectMapper = new ObjectMapper();
        String newAnimalJson = objectMapper.writeValueAsString(animalRequest);

        // On envoie le POST pour le créer
        String responseBody = this.mockMvc
            .perform(MockMvcRequestBuilders.post("/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newAnimalJson))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        // On extrait l'ID retourné depuis le JSON
        Integer idAnimal = objectMapper.readTree(responseBody).get("id").asInt();

        // On fait le GET pour récupérer cet animal
        this.mockMvc
            .perform(get("/animal/" + idAnimal))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Rex"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.race").value("Chien"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Un chien fidèle"));
    }
    

}
