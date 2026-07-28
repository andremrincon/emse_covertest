package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void textDirectoryWithTxtSuffixReturns1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "health-check-" + uuid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "file-" + uuid + ".txt").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void acrobatDirectoryWithPdfSuffixReturns2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document-" + uuid + ".pdf").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void wordDirectoryWithDocSuffixReturns3() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "init-" + uuid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter-" + uuid + ".doc").then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void binDirectoryWithExeSuffixReturns4() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uuid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "program-" + uuid + ".exe").then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void libDirectoryWithDllSuffixReturns5() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prep-" + uuid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library-" + uuid + ".dll").then().assertThat().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void fileWithNoSuffixReturns0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prime-" + uuid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "anydir", "README-" + uuid).then().assertThat().body(equalTo("0"));
    }
}