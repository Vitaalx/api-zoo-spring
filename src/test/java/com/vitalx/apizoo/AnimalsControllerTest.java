package com.vitalx.apizoo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAnimals() throws Exception {

        mockMvc.perform(get("/api-zoo/animals")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("Milou")));

    }

    @Test
    public void testGetDataFromApiRna() throws Exception{
        /*URL url = new URL("https://entreprise.data.gouv.fr/api/rna/v1/full_text/DYNAMICLUB");
        //URLConnection connection = url.openConnection();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())))
        {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }*/
    }
}
