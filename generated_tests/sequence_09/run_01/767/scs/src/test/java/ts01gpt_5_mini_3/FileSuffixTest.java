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
        String base = System.getProperty("API_BASE");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "hc-"+uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "document.txt");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "hc-"+uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "file.pdf");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "hc-"+uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "report.doc");
        assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "hc-"+uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "program.exe");
        assertEquals("4", act.asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "hc-"+uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        assertEquals("5", act.asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "hc-"+uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nofile");
        assertEquals("0", act.asString());
    }
}