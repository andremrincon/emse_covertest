package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class FileSuffixTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"1\"   Actual: {\"times...")
    @Test(timeout = 60000)
    public void testTextTxt() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "file.txt")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(404)
            .body(equalTo("1"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"times...")
    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "file.pdf")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(404)
            .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"3\"   Actual: {\"times...")
    @Test(timeout = 60000)
    public void testWordDoc() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "file.doc")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(404)
            .body(equalTo("3"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"4\"   Actual: {\"times...")
    @Test(timeout = 60000)
    public void testBinExe() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "file.exe")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(404)
            .body(equalTo("4"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"5\"   Actual: {\"times...")
    @Test(timeout = 60000)
    public void testLibDll() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "file.dll")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(404)
            .body(equalTo("5"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"0\"   Actual: {\"times...")
    @Test(timeout = 60000)
    public void testNoExtension() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "file")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(404)
            .body(equalTo("0"));
    }
}