package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/{txt}", "setup-text").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "note.txt");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/{txt}", "setup-acrobat").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/{txt}", "setup-word").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "document.doc");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/{txt}", "setup-bin").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "program.exe");
        assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/{txt}", "setup-lib").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "setup-nosuffix").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "Makefile");
        assertEquals("0", resp.getBody().asString());
    }
}