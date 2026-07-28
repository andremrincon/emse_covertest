package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFileSuffixNoExtension() {
        String directory = "text";
        String file = "file";

        given()
            .pathParam("directory", directory)
            .pathParam("file", file)
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixTextTxt() {
        String directory = "text";
        String file = "file.txt";

        given()
            .pathParam("directory", directory)
            .pathParam("file", file)
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFileSuffixAcrobatPdf() {
        String directory = "acrobat";
        String file = "file.pdf";

        given()
            .pathParam("directory", directory)
            .pathParam("file", file)
        .when()
            .get("/api/filesuffix/{directory}/{file}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }
}