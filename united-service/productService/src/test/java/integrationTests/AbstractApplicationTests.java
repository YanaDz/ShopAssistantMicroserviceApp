package integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.dziadkouskaya.graphql.ProductServiceApplication;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;
import pl.dziadkouskaya.graphql.repository.sql.FirmRepository;

@SpringBootTest(classes = ProductServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
abstract class AbstractApplicationTests {
    @Container
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres");

    @DynamicPropertySource
    static void setProperty(DynamicPropertyRegistry property) {
        property.add("spring.datasource.url", container::getJdbcUrl);
        property.add("spring.datasource.username", container::getUsername);
        property.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected FirmRepository firmRepository;

    public static final String PATH_FIRM_CONTROLLER = "/firm";


    public static FirmParams createFirmParams(String name) {
        return FirmParams.builder()
                .name(name)
                .build();
    }


}
