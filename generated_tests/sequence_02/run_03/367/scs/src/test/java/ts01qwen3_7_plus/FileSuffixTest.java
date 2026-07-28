package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        given()
            .when()
                .get("/api/filesuffix/text/document.txt")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/report.pdf")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
            .when()
                .get("/api/filesuffix/word/letter.doc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        given()
            .when()
                .get("/api/filesuffix/bin/program.exe")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffix() {
        given()
            .when()
                .get("/api/filesuffix/lib/library.dll")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoMatchingDirectoryOrSuffix() {
        given()
            .when()
                .get("/api/filesuffix/unknown/file.xyz")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}