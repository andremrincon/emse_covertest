package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testNoExtension() {
        given()
            .when()
                .get(BASE_URL + "/api/filesuffix/text/noextension")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTextTxt() {
        given()
            .when()
                .get(BASE_URL + "/api/filesuffix/text/file.txt")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        given()
            .when()
                .get(BASE_URL + "/api/filesuffix/acrobat/file.pdf")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        given()
            .when()
                .get(BASE_URL + "/api/filesuffix/word/file.doc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        given()
            .when()
                .get(BASE_URL + "/api/filesuffix/bin/file.exe")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        given()
            .when()
                .get(BASE_URL + "/api/filesuffix/lib/file.dll")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }
}