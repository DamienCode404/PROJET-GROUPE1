package formation_sopra.Refuge;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import formation_sopra.Refuge.dao.IDAOEspece;
import formation_sopra.Refuge.model.Espece;
import formation_sopra.Refuge.rest.EspeceRestController;

@WebMvcTest(EspeceRestController.class)
public class EspeceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDAOEspece daoEspece;

    @Autowired
    private ObjectMapper objectMapper;

    private Espece espece;

    @BeforeEach
    public void setUp() {
        espece = new Espece("Grenouille");
        espece.setId(1);
    }

    @Test
    public void testGetAllEspeces() throws Exception {
        when(daoEspece.findAll()).thenReturn(List.of(espece));

        mockMvc.perform(get("/espece"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].libelle").value("Grenouille"));
    }

    @Test
    public void testGetEspeceById() throws Exception {
        when(daoEspece.findById(1)).thenReturn(Optional.of(espece));

        mockMvc.perform(get("/espece/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.libelle").value("Grenouille"));
    }

    @Test
    public void testCreateEspece() throws Exception {
        when(daoEspece.save(any(Espece.class))).thenReturn(espece);

        mockMvc.perform(post("/espece")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(espece)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.libelle").value("Grenouille"));
    }

    @Test
    public void testUpdateEspece() throws Exception {
        Espece updated = new Espece("Lion");
        updated.setId(1);

        when(daoEspece.existsById(1)).thenReturn(true);
        when(daoEspece.save(any(Espece.class))).thenReturn(updated);

        mockMvc.perform(put("/espece/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.libelle").value("Lion"));
    }

    @Test
    public void testDeleteEspece() throws Exception {
        when(daoEspece.existsById(1)).thenReturn(true);

        mockMvc.perform(delete("/espece/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEspeceById_NotFound() throws Exception {
        when(daoEspece.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/espece/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateEspece_IncoherentId() throws Exception {
        Espece incoherent = new Espece("Panthère");
        incoherent.setId(2);

        mockMvc.perform(put("/espece/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incoherent)))
                .andExpect(status().isBadRequest());
    }
}