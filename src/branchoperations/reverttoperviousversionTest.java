package branchoperations;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class reverttoperviousversionTest {



    @BeforeEach
    void setUp() {new loadenv.loadenv();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void reverttoperviousversionTest()  {

        try {
            new reverttoperviousversion("npp","Main");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}