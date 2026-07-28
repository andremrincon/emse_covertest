package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            Map<String, String> env = System.getenv();
            base = env.getOrDefault("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/text/file.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/acrobat/report.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/word/document.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/bin/program.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/lib/module.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/ready2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/any/README");
        act.then().body(equalTo("0"));
    }
}