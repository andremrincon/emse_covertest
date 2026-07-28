package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFileWithNoDotReturnsZero() {
        given()
            .when()
                .get("/api/filesuffix/text/nofile")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturnsOne() {
        given()
            .when()
                .get("/api/filesuffix/text/report.txt")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturnsTwo() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/document.pdf")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturnsThree() {
        given()
            .when()
                .get("/api/filesuffix/word/letter.doc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturnsFour() {
        given()
            .when()
                .get("/api/filesuffix/bin/program.exe")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturnsFive() {
        given()
            .when()
                .get("/api/filesuffix/lib/library.dll")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }
}