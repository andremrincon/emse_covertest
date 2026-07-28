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
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response resp = given().pathParam("directory", "text").pathParam("file", "document.txt").when().get("/api/filesuffix/{directory}/{file}");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().pathParam("directory", "acrobat").pathParam("file", "report.pdf").when().get("/api/filesuffix/{directory}/{file}");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/{txt}", "prepare").then().statusCode(lessThan(300));
        Response resp = given().pathParam("directory", "word").pathParam("file", "letter.doc").when().get("/api/filesuffix/{directory}/{file}");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/{txt}", "bootstrap").then().statusCode(lessThan(300));
        Response resp = given().pathParam("directory", "bin").pathParam("file", "installer.exe").when().get("/api/filesuffix/{directory}/{file}");
        assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/{txt}", "seed").then().statusCode(lessThan(300));
        Response resp = given().pathParam("directory", "lib").pathParam("file", "library.dll").when().get("/api/filesuffix/{directory}/{file}");
        assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "init").then().statusCode(lessThan(300));
        Response resp = given().pathParam("directory", "text").pathParam("file", "nosuffixfile").when().get("/api/filesuffix/{directory}/{file}");
        assertEquals("0", resp.getBody().asString());
    }
}