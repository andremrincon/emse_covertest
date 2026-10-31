package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testTextTxtSuffix() {
        given()
            .when()
                .get("/api/filesuffix/text/file.txt")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfSuffix() {
        given()
            .when()
                .get("/api/filesuffix/acrobat/file.pdf")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocSuffix() {
        given()
            .when()
                .get("/api/filesuffix/word/file.doc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeSuffix() {
        given()
            .when()
                .get("/api/filesuffix/bin/file.exe")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllSuffix() {
        given()
            .when()
                .get("/api/filesuffix/lib/file.dll")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoDotFileReturnsZero() {
        given()
            .when()
                .get("/api/filesuffix/text/nofile")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}