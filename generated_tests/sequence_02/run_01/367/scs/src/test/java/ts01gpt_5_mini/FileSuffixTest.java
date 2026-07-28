package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            String eb = System.getenv("BASE_URL");
            RestAssured.baseURI = (eb == null || eb.isEmpty()) ? "http://localhost:8080" : eb;
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", uid + ".txt").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", uid + ".pdf").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", uid + ".doc").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", uid + ".exe").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", uid + ".dll").then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixOrUnknownDirectoryReturns0() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "unknown", uid).then().body(equalTo("0"));
    }
}