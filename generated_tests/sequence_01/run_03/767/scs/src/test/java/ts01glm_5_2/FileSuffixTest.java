package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFileWithNoDotReturnsZero() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "nofile")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTextDirectoryTxtSuffixReturnsOne() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "document.txt")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryPdfSuffixReturnsTwo() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "document.pdf")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryDocSuffixReturnsThree() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "document.doc")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryExeSuffixReturnsFour() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "program.exe")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryDllSuffixReturnsFive() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "library.dll")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }
}