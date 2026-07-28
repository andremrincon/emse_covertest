package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @Test(timeout = 60000)
    public void testWordDoc() {
        given()
            .when()
                .get("/api/filesuffix/word/a.doc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testWordTxt() {
        given()
            .when()
                .get("/api/filesuffix/word/a.txt")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        given()
            .when()
                .get("/api/filesuffix/bin/a.exe")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testBinTxt() {
        given()
            .when()
                .get("/api/filesuffix/bin/a.txt")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        given()
            .when()
                .get("/api/filesuffix/lib/a.dll")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testLibTxt() {
        given()
            .when()
                .get("/api/filesuffix/lib/a.txt")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}