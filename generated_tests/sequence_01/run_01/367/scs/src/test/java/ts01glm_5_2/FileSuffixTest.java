package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
    public void testFileWithoutDotReturnsZero() {
        given()
            .when()
                .get("/api/filesuffix/text/noextension")
            .then()
                .statusCode(200);
    }
}