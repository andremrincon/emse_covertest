package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFile() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .when()
                .get("/api/filesuffix/text/file.txt")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfFile() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .when()
                .get("/api/filesuffix/acrobat/file.pdf")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocFile() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .when()
                .get("/api/filesuffix/word/file.doc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeFile() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .when()
                .get("/api/filesuffix/bin/file.exe")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllFile() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .when()
                .get("/api/filesuffix/lib/file.dll")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }
}