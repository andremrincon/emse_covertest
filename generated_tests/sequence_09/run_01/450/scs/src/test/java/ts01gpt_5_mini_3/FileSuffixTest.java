package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import java.util.UUID;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        String base = env != null && !env.isEmpty() ? env : System.getProperty("api.base", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = uuid + ".txt";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        String body = resp.asString();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/ABABCABAB/ABAB").then().statusCode(lessThan(300));
        String file = uuid + ".pdf";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        String body = resp.asString();
        Assert.assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        String file = uuid + ".doc";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", file);
        String body = resp.asString();
        Assert.assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String file = uuid + ".exe";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        String body = resp.asString();
        Assert.assertEquals("4", body);
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        String file = uuid + ".dll";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        String body = resp.asString();
        Assert.assertEquals("5", body);
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = uuid;
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        String body = resp.asString();
        Assert.assertEquals("0", body);
    }
}