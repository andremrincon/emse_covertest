package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testLastPartLessThanOrEqualToZero() {
        Response response = given().when().get("/api/filesuffix/text/noext");
        assertEquals("0", response.body().asString());
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        Response response = given().when().get("/api/filesuffix/text/a.txt");
        assertEquals("1", response.body().asString());
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithNonTxtSuffix() {
        Response response = given().when().get("/api/filesuffix/text/a.doc");
        assertEquals("0", response.body().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        Response response = given().when().get("/api/filesuffix/acrobat/a.pdf");
        assertEquals("2", response.body().asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        Response response = given().when().get("/api/filesuffix/word/a.doc");
        assertEquals("3", response.body().asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        Response response = given().when().get("/api/filesuffix/bin/a.exe");
        assertEquals("4", response.body().asString());
    }
}