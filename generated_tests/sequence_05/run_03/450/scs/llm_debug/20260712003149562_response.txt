package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "unique-" + java.util.UUID.randomUUID() + ".txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "doc-" + java.util.UUID.randomUUID() + ".pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "file-" + java.util.UUID.randomUUID() + ".doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "app-" + java.util.UUID.randomUUID() + ".exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "lib-" + java.util.UUID.randomUUID() + ".dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixOrSinglePartFileReturns0() {
        given().when().get("/api/pat/{txt}", "start").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "misc", "README-" + java.util.UUID.randomUUID());
        act.then().body(equalTo("0"));
    }
}