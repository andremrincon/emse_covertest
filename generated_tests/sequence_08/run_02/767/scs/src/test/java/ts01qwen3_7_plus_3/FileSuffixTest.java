package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTextTxt() {
        given()
            .when()
            .get("/api/filesuffix/text/file.txt")
            .then()
            .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        given()
            .when()
            .get("/api/filesuffix/acrobat/file.pdf")
            .then()
            .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        given()
            .when()
            .get("/api/filesuffix/word/file.doc")
            .then()
            .body(containsString("3"));
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        given()
            .when()
            .get("/api/filesuffix/bin/file.exe")
            .then()
            .body(containsString("4"));
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        given()
            .when()
            .get("/api/filesuffix/lib/file.dll")
            .then()
            .body(containsString("5"));
    }
}