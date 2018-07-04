package versioning;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

class versioningStagingTest {

    @BeforeEach
    void setUp() {
        new loadenv.loadenv();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printfile() throws IOException, NoSuchAlgorithmException, InterruptedException {

//        versioning_staging.printfile(new File("C:\\Users\\sanjeev halyal\\PhpstormProjects\\netpay"));

        String path = "C:\\Users\\sanjeev halyal\\PhpstormProjects\\npp";
        String branch="Main";



        versioning_staging.initalize(path,branch);
    }
}