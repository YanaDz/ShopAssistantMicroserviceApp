package integrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.dziadkouskaya.graphql.ProductServiceApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductServiceApplication.class)
public class FirmControllerTests extends AbstractApplicationTests {

    @Test
    void testFirmCreation() throws Exception {
        var firmName = "Firm1";
        var firmParams = createFirmParams(firmName);
        var firmParamString = objectMapper.writeValueAsString(firmParams);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH_FIRM_CONTROLLER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firmParamString))
                .andExpect(status().isCreated());

        var entity = firmRepository.findAll();
        assertEquals(1, entity.size());
        assertEquals(firmName, entity.stream().findFirst().get().getName());

    }


}
