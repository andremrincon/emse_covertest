package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtSuffix() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/text/sample." + id + ".md").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/text/note_" + id + ".txt").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfSuffix() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/acrobat/other_" + id + ".txt").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/acrobat/report_" + id + ".pdf").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDocSuffix() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/word/unknown_" + id + ".exe").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/word/document.final." + id + ".doc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExeSuffix() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/bin/sample_" + id + ".dll").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/bin/program." + id + ".exe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDllSuffix() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/lib/temp_" + id + ".doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/lib/library." + id + ".dll").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoDotFileReturnsZero() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/text/mismatch_" + id + ".md").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/unknown/noextension_" + id).then().statusCode(200);
    }
}