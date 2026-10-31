package ts01glm_5_2_3;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFileWithoutDotReturnsZero() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/filesuffix/text/nofile")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/filesuffix/text/report.txt")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/filesuffix/acrobat/doc.pdf")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/filesuffix/word/file.doc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/filesuffix/bin/app.exe")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffix() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/filesuffix/lib/library.dll")
        .then()
            .statusCode(200)
            .body(equalTo("5"));
    }
}