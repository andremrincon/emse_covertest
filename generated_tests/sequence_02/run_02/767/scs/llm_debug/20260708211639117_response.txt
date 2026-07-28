package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    private String baseUrl() {
        String v = System.getProperty("api.base");
        if (v == null || v.isEmpty()) v = System.getenv("API_BASE");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        return v;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        String base = baseUrl();
        String runId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", runId).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "text", "document.txt");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        String base = baseUrl();
        String runId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", runId).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "acrobat", "paper.pdf");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        String base = baseUrl();
        String runId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", runId).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "word", "resume.doc");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        String base = baseUrl();
        String runId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", runId).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "bin", "program.exe");
        assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        String base = baseUrl();
        String runId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", runId).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFileWithoutSuffixReturns0() {
        String base = baseUrl();
        String runId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", runId).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "anydir", "README");
        assertEquals("0", resp.getBody().asString());
    }
}