package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
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
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "notes.txt").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "report.doc").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer.exe").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll").then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "file").then().body(equalTo("0"));
    }
}