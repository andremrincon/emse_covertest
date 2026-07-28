package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFileSuffixText() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "document.txt")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixAcrobat() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "document.pdf")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixWord() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "document.doc")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixBin() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "program.exe")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixLib() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "library.dll")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("5"));
    }
}