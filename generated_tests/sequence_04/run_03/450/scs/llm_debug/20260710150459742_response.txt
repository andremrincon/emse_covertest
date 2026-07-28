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
        String base = System.getProperty("BASE_URL", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        given().when().get("/api/pat/{txt}", "healthcheck-no-suffix").then().statusCode(lessThan(300));
        String fname = "file-" + UUID.randomUUID().toString();
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", fname);
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testTextTxtReturnsOne() {
        given().when().get("/api/pat/{txt}", "healthcheck-text-txt").then().statusCode(lessThan(300));
        String fname = "document-" + UUID.randomUUID().toString() + ".txt";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", fname);
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturnsTwo() {
        given().when().get("/api/pat/{txt}", "healthcheck-acrobat-pdf").then().statusCode(lessThan(300));
        String fname = "ebook-" + UUID.randomUUID().toString() + ".pdf";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", fname);
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testWordDocReturnsThree() {
        given().when().get("/api/pat/{txt}", "healthcheck-word-doc").then().statusCode(lessThan(300));
        String fname = "letter-" + UUID.randomUUID().toString() + ".doc";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", fname);
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void testBinExeReturnsFour() {
        given().when().get("/api/pat/{txt}", "healthcheck-bin-exe").then().statusCode(lessThan(300));
        String fname = "program-" + UUID.randomUUID().toString() + ".exe";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", fname);
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("4", body);
    }

    @Test(timeout = 60000)
    public void testLibDllReturnsFive() {
        given().when().get("/api/pat/{txt}", "healthcheck-lib-dll").then().statusCode(lessThan(300));
        String fname = "module-" + UUID.randomUUID().toString() + ".dll";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", fname);
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("5", body);
    }
}