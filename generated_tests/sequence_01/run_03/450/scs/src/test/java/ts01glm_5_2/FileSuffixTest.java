package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testNoDotFileReturnsZero() {
        given()
                .when()
                .get("/api/filesuffix/text/nodotfile")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturnsOne() {
        given()
                .when()
                .get("/api/filesuffix/text/report.txt")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturnsTwo() {
        given()
                .when()
                .get("/api/filesuffix/acrobat/document.pdf")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturnsThree() {
        given()
                .when()
                .get("/api/filesuffix/word/letter.doc")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturnsFour() {
        given()
                .when()
                .get("/api/filesuffix/bin/application.exe")
                .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturnsFive() {
        given()
                .when()
                .get("/api/filesuffix/lib/library.dll")
                .then()
                .statusCode(200)
                .body(equalTo("5"));
    }
}