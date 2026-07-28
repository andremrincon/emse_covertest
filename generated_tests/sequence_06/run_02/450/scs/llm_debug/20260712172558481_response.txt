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
        String env = System.getenv("BASE_URL");
        String base = System.getProperty("baseUrl", env != null ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "document.txt");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        assertEquals("3", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        assertEquals("5", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nofile");
        assertEquals("0", act.getBody().asString());
    }
}