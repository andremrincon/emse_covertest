package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    @Test(timeout = 60000)
    public void testFileSuffixNoExtension() {
        given()
            .when()
                .get("/api/filesuffix/text/file")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixText() {
        given()
            .when()
                .get("/api/filesuffix/text/file.txt")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixAcrobat() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/file.pdf")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixWord() {
        given()
            .when()
                .get("/api/filesuffix/word/file.doc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixBin() {
        given()
            .when()
                .get("/api/filesuffix/bin/file.exe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileSuffixLib() {
        given()
            .when()
                .get("/api/filesuffix/lib/file.dll")
            .then()
                .statusCode(200);
    }
}