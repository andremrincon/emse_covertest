package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.trim().isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testTextTxt() {
        given()
            .when()
                .get("/api/filesuffix/text/doc.txt")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/doc.pdf")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        given()
            .when()
                .get("/api/filesuffix/word/doc.doc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        given()
            .when()
                .get("/api/filesuffix/bin/doc.exe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        given()
            .when()
                .get("/api/filesuffix/lib/doc.dll")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoSuffix() {
        given()
            .when()
                .get("/api/filesuffix/text/doc")
            .then()
                .statusCode(200);
    }
}