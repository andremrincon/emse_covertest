package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    private String base() {
        String b = System.getenv("BASE_URL");
        if (b != null && !b.isEmpty()) return b;
        b = System.getProperty("baseUrl");
        if (b != null && !b.isEmpty()) return b;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().baseUri(base).when().get("/api/pat/{txt}", "healthcheck-" + uuid).then().statusCode(lessThan(300));
        String file = "sample-" + uuid + ".txt";
        Response resp = given().baseUri(base).when().get("/api/filesuffix/{directory}/{file}", "text", file);
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().baseUri(base).when().get("/api/pat/{txt}", "healthcheck-" + uuid).then().statusCode(lessThan(300));
        String file = "doc-" + uuid + ".pdf";
        Response resp = given().baseUri(base).when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().baseUri(base).when().get("/api/pat/{txt}", "healthcheck-" + uuid).then().statusCode(lessThan(300));
        String file = "report-" + uuid + ".doc";
        Response resp = given().baseUri(base).when().get("/api/filesuffix/{directory}/{file}", "word", file);
        assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().baseUri(base).when().get("/api/pat/{txt}", "healthcheck-" + uuid).then().statusCode(lessThan(300));
        String file = "app-" + uuid + ".exe";
        Response resp = given().baseUri(base).when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().baseUri(base).when().get("/api/pat/{txt}", "healthcheck-" + uuid).then().statusCode(lessThan(300));
        String file = "library-" + uuid + ".dll";
        Response resp = given().baseUri(base).when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        assertEquals("5", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().baseUri(base).when().get("/api/pat/{txt}", "healthcheck-" + uuid).then().statusCode(lessThan(300));
        String file = "nosuffix-" + uuid;
        Response resp = given().baseUri(base).when().get("/api/filesuffix/{directory}/{file}", "word", file);
        assertEquals("0", resp.asString());
    }
}