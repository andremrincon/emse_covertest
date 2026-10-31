package ts01glm_5_2_2;

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
    public void testNoDotInFilenameReturnsZero() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "nofile")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturnsOne() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "file.txt")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturnsTwo() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "file.pdf")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturnsThree() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "file.doc")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturnsFour() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "file.exe")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturnsFive() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "file.dll")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .body(equalTo("5"));
    }
}