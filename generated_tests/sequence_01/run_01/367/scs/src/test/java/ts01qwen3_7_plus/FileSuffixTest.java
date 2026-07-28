package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFile() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/filesuffix/{directory}/{file}", "text", "document.txt")
                .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfFile() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf")
                .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocFile() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/filesuffix/{directory}/{file}", "word", "document.doc")
                .then()
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeFile() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/filesuffix/{directory}/{file}", "bin", "document.exe")
                .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllFile() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/filesuffix/{directory}/{file}", "lib", "document.dll")
                .then()
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithNonTxtFile() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/api/filesuffix/{directory}/{file}", "text", "document.pdf")
                .then()
                .body(equalTo("0"));
    }
}