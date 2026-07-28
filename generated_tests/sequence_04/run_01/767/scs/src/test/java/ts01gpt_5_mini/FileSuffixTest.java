package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFileReturns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "document.txt");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfFileReturns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocFileReturns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "resume.doc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeFileReturns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllFileReturns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFileWithoutSuffixReturns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nofileextension");
        assertEquals(200, resp.getStatusCode());
    }
}