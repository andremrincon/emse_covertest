package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        given()
            .when()
                .get("/api/filesuffix/text/file.txt")
            .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/file.pdf")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
            .when()
                .get("/api/filesuffix/word/file.doc")
            .then()
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        given()
            .when()
                .get("/api/filesuffix/bin/file.exe")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffix() {
        given()
            .when()
                .get("/api/filesuffix/lib/file.dll")
            .then()
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testFileWithoutDotReturnsZero() {
        given()
            .when()
                .get("/api/filesuffix/text/fileNoDot")
            .then()
                .body(equalTo("0"));
    }
}