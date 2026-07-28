package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "report.txt");
        Assert.assertEquals("1", resp.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "doc.pdf");
        Assert.assertEquals("2", resp.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void testWordDocReturnsThree() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        Assert.assertEquals("3", resp.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void testBinExeReturnsFour() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        Assert.assertEquals("4", resp.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void testLibDllReturnsFive() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        Assert.assertEquals("5", resp.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void testNoDotReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nofile");
        Assert.assertEquals("0", resp.getBody().asString().replace("\"", ""));
    }
}