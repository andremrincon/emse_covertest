package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    private static String baseUri;

    @BeforeClass
    public static void setup() {
        baseUri = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUri;
    }

    @Test(timeout = 60000)
    public void testNoExtension() {
        Response response = given()
                .baseUri(baseUri)
                .when()
                .get("/api/filesuffix/text/noextension");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTextTxt() {
        Response response = given()
                .baseUri(baseUri)
                .when()
                .get("/api/filesuffix/text/file.txt");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        Response response = given()
                .baseUri(baseUri)
                .when()
                .get("/api/filesuffix/acrobat/file.pdf");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        Response response = given()
                .baseUri(baseUri)
                .when()
                .get("/api/filesuffix/word/file.doc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        Response response = given()
                .baseUri(baseUri)
                .when()
                .get("/api/filesuffix/bin/file.exe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        Response response = given()
                .baseUri(baseUri)
                .when()
                .get("/api/filesuffix/lib/file.dll");
        response.then().statusCode(200);
    }
}