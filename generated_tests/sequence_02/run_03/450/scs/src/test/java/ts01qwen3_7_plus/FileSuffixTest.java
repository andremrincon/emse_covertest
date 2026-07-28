package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testFileSuffixNoExtension() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/text/file")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixTextTxt() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/text/file.txt")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixAcrobatPdf() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/acrobat/file.pdf")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixWordDoc() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/word/file.doc")
        .then()
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixBinExe() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/bin/file.exe")
        .then()
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixLibDll() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/filesuffix/lib/file.dll")
        .then()
            .body(equalTo("5"));
    }
}