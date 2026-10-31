package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix_returns1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/sample" + uuid + ".txt");
        assertEquals("1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix_returns2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/acrobat/document" + uuid + ".pdf");
        assertEquals("2", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix_returns3() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/word/report" + uuid + ".doc");
        assertEquals("3", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix_returns4() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/bin/tool" + uuid + ".exe");
        assertEquals("4", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffix_returns5() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/lib/library" + uuid + ".dll");
        assertEquals("5", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoSuffixFile_returns0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/unknown/README" + uuid);
        assertEquals("0", resp.getBody().asString().trim());
    }
}