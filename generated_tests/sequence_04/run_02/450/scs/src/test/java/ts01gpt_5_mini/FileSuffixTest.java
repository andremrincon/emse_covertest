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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "note.txt");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        given().when().get("/api/pat/{txt}", "health-check-2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        given().when().get("/api/pat/{txt}", "health-check-3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "document.doc");
        assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        given().when().get("/api/pat/{txt}", "health-check-4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        assertEquals("4", act.asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        given().when().get("/api/pat/{txt}", "health-check-5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        assertEquals("5", act.asString());
    }

    @Test(timeout = 60000)
    public void testFileWithNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "health-check-6").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "misc", "README");
        assertEquals("0", act.asString());
    }
}