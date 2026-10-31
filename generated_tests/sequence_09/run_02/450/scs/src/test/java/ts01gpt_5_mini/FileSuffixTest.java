package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        String directory = "text";
        String file = "file-" + uuid + ".txt";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", directory, file);
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        String directory = "acrobat";
        String file = "doc-" + uuid + ".pdf";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", directory, file);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturnsThree() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        String directory = "word";
        String file = "report-" + uuid + ".doc";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", directory, file);
        assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturnsFour() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        String directory = "bin";
        String file = "run-" + uuid + ".exe";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", directory, file);
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturnsFive() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        String directory = "lib";
        String file = "library-" + uuid + ".dll";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", directory, file);
        assertEquals("5", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixOrNonMatchingSuffixReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        String directory = "text";
        String file = "nosuffix-" + uuid;
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", directory, file);
        assertEquals("0", resp.asString());
    }
}