package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturnsOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".txt";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturnsOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".pdf";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturnsOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String file = "report." + UUID.randomUUID().toString() + ".doc";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", file);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturnsOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String file = "run-" + UUID.randomUUID().toString() + ".exe";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturnsOk() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String file = "lib_" + UUID.randomUUID().toString() + ".dll";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsOkZeroCase() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String file = "nosuffix" + UUID.randomUUID().toString();
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        resp.then().statusCode(200);
    }
}