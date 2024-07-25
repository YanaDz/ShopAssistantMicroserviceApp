package integrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.dziadkouskaya.graphql.ProductServiceApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductServiceApplication.class)
public class FirmControllerTests extends AbstractApplicationTests {

    @Test
    void testFirmCreation() throws Exception {
        var firmParams = createFirmParams("Firm1");
        var firmParamString = objectMapper.writeValueAsString(firmParams);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH_FIRM_CONTROLLER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(firmParamString))
                .andExpect(status().isCreated());


    }
}
