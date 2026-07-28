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
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "file.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "report.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        given().when().get("/api/pat/{txt}", "ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "module.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nosuffix");
        act.then().body(equalTo("0"));
    }
}