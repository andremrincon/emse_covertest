package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {
    private static final String BASE;
    static {
        String v = System.getProperty("BASE_URL");
        if (v == null || v.isEmpty()) v = System.getProperty("base.url");
        if (v == null || v.isEmpty()) v = System.getenv("BASE_URL");
        if (v == null || v.isEmpty()) v = System.getenv("base.url");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        BASE = v;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-"+unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "document-"+unique+".txt");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-"+unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report-"+unique+".pdf");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-"+unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter-"+unique+".doc");
        assertEquals("3", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-"+unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "app-"+unique+".exe");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-"+unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library-"+unique+".dll");
        assertEquals("5", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-"+unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "unknown", "readme-"+unique);
        assertEquals("0", act.getBody().asString());
    }
}