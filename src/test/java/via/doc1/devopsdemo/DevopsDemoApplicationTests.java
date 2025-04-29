package via.doc1.devopsdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Use the test profile
class DevopsDemoApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the application context loads successfully
    }
}