package pulloperations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pulllatestTest {

    @BeforeEach
    void setUp() {new loadenv.loadenv();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pulllatest() throws Exception {

        String path = "C:\\Users\\sanjeev halyal\\PhpstormProjects\\npp";
        String branch="Main";
        String projectname="npp";

        new pulllatest(path,branch,projectname);
    }
}