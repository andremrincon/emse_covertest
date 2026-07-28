package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {
    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String file = "file-" + UUID.randomUUID().toString() + ".txt";
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String file = "document-" + UUID.randomUUID().toString() + ".pdf";
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String file = "report-" + UUID.randomUUID().toString() + ".doc";
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", file).then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String file = "binary-" + UUID.randomUUID().toString() + ".exe";
        given().when().get("/api/pat/{txt}", "ok").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", file).then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String file = "library-" + UUID.randomUUID().toString() + ".dll";
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", file).then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoExtensionReturns0() {
        String file = "nofext-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "start").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "unknown", file).then().body(equalTo("0"));
    }
}