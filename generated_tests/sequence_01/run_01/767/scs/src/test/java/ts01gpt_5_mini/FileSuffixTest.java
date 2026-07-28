package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "notes." + UUID.randomUUID().toString() + ".txt");
        String body = act.asString();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document." + UUID.randomUUID().toString() + ".pdf");
        String body = act.asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "contract." + UUID.randomUUID().toString() + ".doc");
        String body = act.asString();
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer." + UUID.randomUUID().toString() + ".exe");
        String body = act.asString();
        assertEquals("4", body);
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native." + UUID.randomUUID().toString() + ".dll");
        String body = act.asString();
        assertEquals("5", body);
    }

    @Test(timeout = 60000)
    public void testNoDotReturns0() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "README" + UUID.randomUUID().toString());
        String body = act.asString();
        assertEquals("0", body);
    }
}