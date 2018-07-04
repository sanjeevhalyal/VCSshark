package initialize;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class initializeTest {

    @BeforeEach
    void setUp() {
        new loadenv.loadenv();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() throws Exception {

        String path = "C:\\Users\\sanjeev halyal\\PhpstormProjects";
        String project="npp";
        new initialize(path,project,"Main").init();
    }
}