package ts01glm_5_2_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    private static String getBaseUrl() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.trim().isEmpty()) {
            url = System.getenv("baseUrl");
        }
        if (url == null || url.trim().isEmpty()) {
            url = "http://localhost:8080";
        }
        return url;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/text/file.txt")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/acrobat/file.pdf")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/word/file.doc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/bin/file.exe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffix() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/lib/file.dll")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileWithoutDotSuffix() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/text/nofiledot")
        .then()
            .statusCode(200);
    }
}