package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/text/sample.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/acrobat/document.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/word/report.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/bin/program.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/lib/library.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/any/file");
        act.then().body(equalTo("0"));
    }
}