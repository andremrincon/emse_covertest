package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxt_returns1() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "note.txt").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf_returns2() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc_returns3() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "document.doc").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExe_returns4() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "program.exe").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDll_returns5() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll").then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffix_returns0() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "file").then().body(equalTo("0"));
    }
}