package ts01gpt_5_mini_3;

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
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void textDirectoryWithTxtSuffixReturns1() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "note-" + uid + ".txt");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void acrobatDirectoryWithPdfSuffixReturns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "manual-" + uid + ".pdf");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void wordDirectoryWithDocSuffixReturns3() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "contract-" + uid + ".doc");
        assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void binDirectoryWithExeSuffixReturns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer-" + uid + ".exe");
        assertEquals("4", act.asString());
    }

    @Test(timeout = 60000)
    public void libDirectoryWithDllSuffixReturns5() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library-" + uid + ".dll");
        assertEquals("5", act.asString());
    }

    @Test(timeout = 60000)
    public void fileWithoutSuffixReturns0() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "README-" + uid);
        assertEquals("0", act.asString());
    }
}