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
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + id).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "text", "file-" + id + ".txt");
        assertEquals("1", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + id).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "doc-" + id + ".pdf");
        assertEquals("2", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + id).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "word", "report-" + id + ".doc");
        assertEquals("3", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + id).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run-" + id + ".exe");
        assertEquals("4", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + id).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library-" + id + ".dll");
        assertEquals("5", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-" + id).then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nosuffix-" + id);
        assertEquals("0", res.getBody().asString());
    }
}