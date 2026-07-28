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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxt_returns1() {
        given().when().get("/api/pat/{txt}", "ping-1").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "text", "document.txt");
        res.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf_returns2() {
        given().when().get("/api/pat/{txt}", "ping-2").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        res.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc_returns3() {
        given().when().get("/api/pat/{txt}", "ping-3").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        res.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExe_returns4() {
        given().when().get("/api/pat/{txt}", "ping-4").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        res.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDll_returns5() {
        given().when().get("/api/pat/{txt}", "ping-5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        res.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffix_returns0() {
        given().when().get("/api/pat/{txt}", "ping-6").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/filesuffix/{directory}/{file}", "other", "nofile");
        res.then().body(equalTo("0"));
    }
}