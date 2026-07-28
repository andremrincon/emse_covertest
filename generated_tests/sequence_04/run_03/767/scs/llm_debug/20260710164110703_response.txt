package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class FileSuffixTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testTextTxtMappingReturns200() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "text", "document.txt");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfMappingReturns200() {
        given().when().get(BASE + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "acrobat", "file.pdf");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testWordDocMappingReturns200() {
        given().when().get(BASE + "/api/pat/sample").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBinExeMappingReturns200() {
        given().when().get(BASE + "/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "bin", "program.exe");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testLibDllMappingReturns200() {
        given().when().get(BASE + "/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "lib", "module.dll");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoDotFileReturns200() {
        given().when().get(BASE + "/api/pat/ready").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "other", "README");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}