package ts01glm_5_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testTextTxtSuffix() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/text/file.txt")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfSuffix() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/acrobat/file.pdf")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocSuffix() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/word/file.doc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeSuffix() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/bin/file.exe")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllSuffix() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/lib/file.dll")
        .then()
            .statusCode(200)
            .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoDotSuffix() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/text/file")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}