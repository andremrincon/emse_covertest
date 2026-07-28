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
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void textDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String file = "file-" + UUID.randomUUID().toString() + ".txt";
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file).then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void acrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String file = "doc-" + UUID.randomUUID().toString() + ".pdf";
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file).then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void wordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String file = "wordfile-" + UUID.randomUUID().toString() + ".doc";
        given().when().get("/api/filesuffix/{directory}/{file}", "word", file).then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void binDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String file = "app-" + UUID.randomUUID().toString() + ".exe";
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", file).then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void libDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String file = "lib-" + UUID.randomUUID().toString() + ".dll";
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", file).then().assertThat().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void noSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String file = "nosuffix-" + UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "anydir", file).then().assertThat().body(equalTo("0"));
    }
}