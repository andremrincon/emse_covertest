package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFileWithoutExtension() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "noextension")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFile() {
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
    public void testAcrobatDirectoryWithPdfFile() {
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
    public void testWordDirectoryWithDocFile() {
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
    public void testBinDirectoryWithExeFile() {
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
    public void testLibDirectoryWithDllFile() {
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