package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.Objects;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    private String baseUrl() {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        if (b.endsWith("/")) b = b.substring(0, b.length() - 1);
        return b;
    }

    @Test(timeout = 60000)
    public void testTextTxt_returns1() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/text/file.txt");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf_returns2() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/acrobat/document.pdf");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordDoc_returns3() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/word/report.doc");
        assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void testBinExe_returns4() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/bin/program.exe");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testLibDll_returns5() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/lib/library.dll");
        assertEquals("5", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoExtension_returns0() {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/text/file");
        assertEquals("0", resp.asString());
    }
}