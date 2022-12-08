package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(TraineeController.class)    //  appel du contrôleur par requêtes http (au lieu de faire référence directement à TraineeController

class TraineeControllerTest {

    final static String BASE_URL = "api/trainee";   // problème, à vérifier

    @Autowired
    MockMvc mockMvc; //  appel du contrôleur par requêtes http (au lieu de faire référence directement à TraineeController)

    @MockBean
    TraineeService traineeService;  // mock service ?

    @Test
    void testGetById_Ok() throws Exception {
        //  **  prepare **
        int id = 123;
        var traineeDto  = TraineeDto.builder()
                .id(id)
                .lastName("Brown")
                .firstName("Emmett")
                .build();

        BDDMockito.given(traineeService.findById(id))
                .willReturn(Optional.of(traineeDto));

        // **   when    **
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/trainee/123")
                        .accept(MediaType.APPLICATION_JSON)
        )
                //     **   then/verify HTTP communication  **
                .andDo(MockMvcResultHandlers.print())   //  log request/response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Brown"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Emmett"));;
;
        //  **  then/verify Mock service has been called    **
        BDDMockito.then(traineeService)
                .should()
                .findById(id);
    }

    @Test
    void testGetById_KoNotFound() throws Exception {
        //  **  prepare ** s'assurer que l'ID est vide
        int id = 0;
        BDDMockito.given(traineeService.findById(id))
                .willReturn(Optional.empty());

        // **   when    **
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/trainee/"+ id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //     **   then/verify HTTP communication  **
                .andDo(MockMvcResultHandlers.print())   //  log request/response
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error ").value("Trainee id "+id+ " not found, try again !"));
                // error est la key (K) du format (K, V) Key Value
        //  **  then/verify Mock service has been called    **
        BDDMockito.then(traineeService)
                .should()
                .findById(id);
    }

}