package branchoperations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class branchdiffTest {

    @BeforeEach
    void setUp() {
        new loadenv.loadenv();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void branchdiff() {

         new latestbranchdiff().latestbranchdiff("npp","Main");
    }
}