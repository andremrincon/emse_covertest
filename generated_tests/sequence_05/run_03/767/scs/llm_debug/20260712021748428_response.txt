package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testTextTxt() {
        String directory = "text";
        String file = "file.txt";

        Response response = given()
                .when()
                .get("/api/filesuffix/{directory}/{file}", directory, file);

        response.then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        String directory = "acrobat";
        String file = "file.pdf";

        Response response = given()
                .when()
                .get("/api/filesuffix/{directory}/{file}", directory, file);

        response.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        String directory = "word";
        String file = "file.doc";

        Response response = given()
                .when()
                .get("/api/filesuffix/{directory}/{file}", directory, file);

        response.then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        String directory = "bin";
        String file = "file.exe";

        Response response = given()
                .when()
                .get("/api/filesuffix/{directory}/{file}", directory, file);

        response.then().statusCode(200).body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        String directory = "lib";
        String file = "file.dll";

        Response response = given()
                .when()
                .get("/api/filesuffix/{directory}/{file}", directory, file);

        response.then().statusCode(200).body(equalTo("5"));
    }
}