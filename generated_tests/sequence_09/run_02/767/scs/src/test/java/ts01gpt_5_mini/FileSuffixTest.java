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
    public static void setup() {
        String cfg = System.getProperty("test.server");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("TEST_SERVER");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        if (cfg.startsWith("http://") || cfg.startsWith("https://")) {
            RestAssured.baseURI = cfg;
        } else {
            RestAssured.baseURI = "http://" + cfg;
        }
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixProducesOne() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        String file = "file-" + id + ".txt";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixProducesTwo() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange2-" + id).then().statusCode(lessThan(300));
        String file = "report-" + id + ".pdf";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixProducesThree() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange3-" + id).then().statusCode(lessThan(300));
        String file = "document-" + id + ".doc";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", file);
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixProducesFour() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange4-" + id).then().statusCode(lessThan(300));
        String file = "program-" + id + ".exe";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixProducesFive() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange5-" + id).then().statusCode(lessThan(300));
        String file = "library-" + id + ".dll";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange6-" + id).then().statusCode(lessThan(300));
        String file = "nosuffixfile-" + id;
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        act.then().body(equalTo("0"));
    }
}