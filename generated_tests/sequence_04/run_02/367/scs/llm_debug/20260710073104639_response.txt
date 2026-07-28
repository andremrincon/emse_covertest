package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturnsOne() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "example."+ "txt");
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturnsTwo() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturnsThree() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "report.doc");
        Assert.assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturnsFour() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "runme.exe");
        Assert.assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturnsFive() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        Assert.assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nosuffix");
        Assert.assertEquals("0", resp.getBody().asString());
    }
}