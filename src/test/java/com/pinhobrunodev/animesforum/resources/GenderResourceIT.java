package com.pinhobrunodev.animesforum.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.dto.gender.InsertGenderDTO;
import com.pinhobrunodev.animesforum.services.GenderService;
import com.pinhobrunodev.animesforum.services.exceptions.DatabaseException;
import com.pinhobrunodev.animesforum.services.exceptions.ResourceNotFoundException;
import com.pinhobrunodev.animesforum.tests.Factory;
import com.pinhobrunodev.animesforum.tests.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GenderResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenderService service;



    //Usado para converter para json
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;


    private Long validId;
    private Long invalidId;

    private String baseUsername;
    private String basePassword;
    private String adminUsername;
    private String adminPassword;


    @BeforeEach
    public void setUp() throws Exception {
        validId = 1L;
        invalidId = 99999L;

        baseUsername = "ze@gmail.com";
        basePassword = "123456";

        adminUsername = "admin@gmail.com";
        adminPassword = "123456";


    }

    @Test
    public void insertShouldReturn401WhenNoUserLogged() throws Exception {

        GenderDTO dto = new GenderDTO(null,"GenderDTO");
        String jsonBody = objectMapper.writeValueAsString(dto);
        ResultActions result =
                mockMvc.perform(post("/genders/save")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void insertShouldInsertResourceWhenAdminLoggedAndCorrectData() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

        InsertGenderDTO dto = new InsertGenderDTO(null,"GenderDTO");
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/genders/save")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));;
            result.andExpect(status().isCreated());
    }


    @Test
    public void insertShouldNotInsertResourceWhenBaseLoggedAndCorrectData() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, baseUsername, basePassword);

        InsertGenderDTO dto = new InsertGenderDTO(null,"GenderDTO");
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(post("/genders/save")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));;
        result.andExpect(status().isForbidden());
    }
}
