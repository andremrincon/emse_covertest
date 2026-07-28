package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setUpClass() {
        String base = System.getProperty("base.url");
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
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/arrange-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/" + uuid + ".txt");
        Assert.assertEquals("1", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/acrobat/" + uuid + ".pdf");
        Assert.assertEquals("2", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/init-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/word/" + uuid + ".doc");
        Assert.assertEquals("3", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/seed-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/bin/" + uuid + ".exe");
        Assert.assertEquals("4", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/prime-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/lib/" + uuid + ".dll");
        Assert.assertEquals("5", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoDotReturns0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/clean-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/" + uuid);
        Assert.assertEquals("0", resp.asString().trim());
    }
}