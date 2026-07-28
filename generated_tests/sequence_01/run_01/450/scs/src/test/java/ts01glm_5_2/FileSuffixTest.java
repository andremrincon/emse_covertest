package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffix() {
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
    public void testAcrobatDirectoryWithPdfSuffix() {
        given()
                .pathParam("directory", "acrobat")
                .pathParam("file", "report.pdf")
                .when()
                .get("/api/filesuffix/{directory}/{file}")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffix() {
        given()
                .pathParam("directory", "word")
                .pathParam("file", "letter.doc")
                .when()
                .get("/api/filesuffix/{directory}/{file}")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffix() {
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
    public void testLibDirectoryWithDllSuffix() {
        given()
                .pathParam("directory", "lib")
                .pathParam("file", "library.dll")
                .when()
                .get("/api/filesuffix/{directory}/{file}")
                .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoDotInFileReturnsZero() {
        given()
                .pathParam("directory", "text")
                .pathParam("file", "nofileextension")
                .when()
                .get("/api/filesuffix/{directory}/{file}")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}