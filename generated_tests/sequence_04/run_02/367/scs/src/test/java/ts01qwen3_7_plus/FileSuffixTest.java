package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFileSuffixTextTxt() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "file.txt")
            .when()
                .get("/api/filesuffix/{directory}/{file}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixAcrobatPdf() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "file.pdf")
            .when()
                .get("/api/filesuffix/{directory}/{file}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixWordDoc() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "file.doc")
            .when()
                .get("/api/filesuffix/{directory}/{file}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixBinExe() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "file.exe")
            .when()
                .get("/api/filesuffix/{directory}/{file}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixLibDll() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "file.dll")
            .when()
                .get("/api/filesuffix/{directory}/{file}")
            .then()
                .statusCode(200);
    }
}