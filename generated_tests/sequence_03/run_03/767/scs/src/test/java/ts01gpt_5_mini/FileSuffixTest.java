package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = System.getProperty("test.server");
        }
        if (url == null || url.isEmpty()) {
            url = System.getenv("TEST_SERVER");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        String file = "sample-" + UUID.randomUUID().toString() + ".txt";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        String file = "doc-" + UUID.randomUUID().toString() + ".pdf";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/setup").then().statusCode(lessThan(300));
        String file = "report-" + UUID.randomUUID().toString() + ".doc";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", file);
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/init").then().statusCode(lessThan(300));
        String file = "program-" + UUID.randomUUID().toString() + ".exe";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        String file = "library-" + UUID.randomUUID().toString() + ".dll";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/prime").then().statusCode(lessThan(300));
        String file = "nosuffix-" + UUID.randomUUID().toString();
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "any", file);
        act.then().body(equalTo("0"));
    }
}