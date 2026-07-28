package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("test.server");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "example.txt");
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "doc.pdf");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "file.doc");
        Assert.assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        Assert.assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        Assert.assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "anything", "nofile");
        Assert.assertEquals("0", resp.getBody().asString());
    }
}