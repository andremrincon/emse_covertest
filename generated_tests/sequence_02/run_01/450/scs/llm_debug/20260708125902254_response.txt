package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTextTxt() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "file.txt")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "file.pdf")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "file.doc")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "file.exe")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "file.dll")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoExtension() {
        given()
            .pathParam("directory", "other")
            .pathParam("file", "file")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }
}