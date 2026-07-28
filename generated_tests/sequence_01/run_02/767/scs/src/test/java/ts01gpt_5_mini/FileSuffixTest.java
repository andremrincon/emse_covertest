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
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String ping = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/sample.txt");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String ping = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/acrobat/document.pdf");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String ping = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/word/report.doc");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String ping = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/bin/program.exe");
        assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String ping = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/lib/module.dll");
        assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String ping = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/README");
        assertEquals("0", resp.getBody().asString());
    }
}