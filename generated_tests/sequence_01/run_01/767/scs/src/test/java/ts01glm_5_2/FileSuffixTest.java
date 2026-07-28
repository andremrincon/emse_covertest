package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        given()
            .when()
                .get("/api/filesuffix/text/report.txt")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/document.pdf")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
            .when()
                .get("/api/filesuffix/word/letter.doc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        given()
            .when()
                .get("/api/filesuffix/bin/program.exe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffix() {
        given()
            .when()
                .get("/api/filesuffix/lib/library.dll")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileWithoutDotReturnsZeroResult() {
        given()
            .when()
                .get("/api/filesuffix/text/nofile")
            .then()
                .statusCode(200);
    }
}