package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTextDirectory() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/text/file.txt")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectory() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/acrobat/file.pdf")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectory() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/word/file.doc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectory() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/bin/file.exe")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectory() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/lib/file.dll")
        .then()
            .statusCode(200)
            .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoExtension() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/filesuffix/text/file")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}