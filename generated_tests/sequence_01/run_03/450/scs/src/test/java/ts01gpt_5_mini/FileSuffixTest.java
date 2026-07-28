package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {
    private static final String BASE;
    static {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        BASE = cfg;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturnsOne() {
        String uid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "acrobat", "any-"+uid+".pdf").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "bin", "prog-"+uid+".exe").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "word", "doc-"+uid+".doc").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "lib", "lib-"+uid+".dll").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "text", "otherfile-"+uid).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "text", "unknown-"+uid+".unknown").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "text", "sample-"+uid+".txt").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfStatus200() {
        String uid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report-"+uid+".pdf").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDocStatus200() {
        String uid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "ping2").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "word", "report-"+uid+".doc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExeStatus200() {
        String uid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "ping3").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "bin", "app-"+uid+".exe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDllStatus200() {
        String uid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "ping4").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "lib", "module-"+uid+".dll").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        String uid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "ping5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "text", "nosuffix-"+uid).then().body(equalTo("0"));
    }
}