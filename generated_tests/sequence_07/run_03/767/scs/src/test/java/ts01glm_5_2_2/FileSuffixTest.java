package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNoDotInFileName() {
        given()
            .when()
            .get("/api/filesuffix/word/nofile")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        given()
            .when()
            .get("/api/filesuffix/text/notes.txt")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
            .when()
            .get("/api/filesuffix/acrobat/doc.pdf")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
            .when()
            .get("/api/filesuffix/word/report.doc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        given()
            .when()
            .get("/api/filesuffix/bin/app.exe")
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
}