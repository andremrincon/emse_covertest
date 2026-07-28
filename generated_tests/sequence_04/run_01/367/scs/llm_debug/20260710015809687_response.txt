package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testFileWithoutExtension() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "noextension")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFile() {
        given()
            .pathParam("directory", "text")
            .pathParam("file", "file.txt")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfFile() {
        given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "file.pdf")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocFile() {
        given()
            .pathParam("directory", "word")
            .pathParam("file", "file.doc")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeFile() {
        given()
            .pathParam("directory", "bin")
            .pathParam("file", "file.exe")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllFile() {
        given()
            .pathParam("directory", "lib")
            .pathParam("file", "file.dll")
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200);
    }
}