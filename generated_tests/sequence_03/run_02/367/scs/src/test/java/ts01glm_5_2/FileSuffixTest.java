package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
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
    public void testNoMatchSuffix() {
        given()
            .when()
            .get("/api/filesuffix/unknown/file.xyz")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}