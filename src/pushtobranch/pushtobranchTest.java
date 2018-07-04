package pushtobranch;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pushtobranchTest {

    @BeforeEach
    void setUp() {
        new loadenv.loadenv();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pushtobranch() throws Exception {
        new pushtobranch("npp","Main");
    }
}