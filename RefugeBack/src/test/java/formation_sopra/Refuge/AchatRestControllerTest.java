package formation_sopra.Refuge;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import formation_sopra.Refuge.dao.IDAOAchat;
import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Achat;
import formation_sopra.Refuge.rest.AchatRestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.mockito.Mockito;

@WebMvcTest(
    controllers = AchatRestController.class,
    excludeAutoConfiguration = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class }
)
@Import(AchatRestControllerTest.MockDaoConfig.class)
public class AchatRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IDAOAchat daoAchat;

    @Autowired
    private IDAOUtilisateur daoUtilisateur;

    @Autowired
    private ObjectMapper objectMapper;

    private Achat achat;

    @BeforeEach
    public void setUp() {
        achat = new Achat(5, 20.0, LocalDate.now());
        achat.setId(1);
    }

    @Test
    public void testGetAllAchat() throws Exception {
        when(daoAchat.findAll()).thenReturn(List.of(achat));

        mockMvc.perform(get("/achat"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].qte").value(5))
                .andExpect(jsonPath("$[0].prix").value(20.0));
    }

    @Test
    public void testCreateAchat() throws Exception {
        when(daoAchat.save(any(Achat.class))).thenReturn(achat);

        mockMvc.perform(post("/achat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(achat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.qte").value(5))
                .andExpect(jsonPath("$.prix").value(20.0));
    }

    @TestConfiguration
    static class MockDaoConfig {

        @Bean
        public IDAOAchat daoAchat() {
            return Mockito.mock(IDAOAchat.class);
        }

        @Bean
        public IDAOUtilisateur daoUtilisateur() {
            return Mockito.mock(IDAOUtilisateur.class);
        }
    }
}