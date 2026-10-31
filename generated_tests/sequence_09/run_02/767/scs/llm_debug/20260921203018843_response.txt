package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String url = System.getenv("TEST_BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getProperty("TEST_BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "sample.txt")
               .then().statusCode(lessThan(300)).and().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf")
               .then().statusCode(lessThan(300)).and().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "report.doc")
               .then().statusCode(lessThan(300)).and().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe")
               .then().statusCode(lessThan(300)).and().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll")
               .then().statusCode(lessThan(300)).and().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "README")
               .then().statusCode(lessThan(300)).and().body(equalTo("0"));
    }
}