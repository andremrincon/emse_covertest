package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "sample.txt");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        given().when().get("/api/pat/{txt}", "healthcheck3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "file.doc");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        given().when().get("/api/pat/{txt}", "healthcheck4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        given().when().get("/api/pat/{txt}", "healthcheck5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixOrUnknownReturns0() {
        given().when().get("/api/pat/{txt}", "healthcheck6").then().statusCode(lessThan(300));
        Response resp1 = given().when().get("/api/filesuffix/{directory}/{file}", "word", "nofile");
        resp1.then().body(equalTo("0"));
        given().when().get("/api/pat/{txt}", "healthcheck7").then().statusCode(lessThan(300));
        Response resp2 = given().when().get("/api/filesuffix/{directory}/{file}", "text", "archive.tar.gz");
        resp2.then().body(equalTo("0"));
    }
}