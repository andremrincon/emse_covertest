package ts01qwen3_7_plus_2;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @Test(timeout = 60000)
    public void testTextTxt() {
        Response response = given()
            .pathParam("directory", "text")
            .pathParam("file", "file.txt")
        .when()
            .get("http://localhost:8080/api/filesuffix/{directory}/{file}");

        response.then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf() {
        Response response = given()
            .pathParam("directory", "acrobat")
            .pathParam("file", "file.pdf")
        .when()
            .get("http://localhost:8080/api/filesuffix/{directory}/{file}");

        response.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc() {
        Response response = given()
            .pathParam("directory", "word")
            .pathParam("file", "file.doc")
        .when()
            .get("http://localhost:8080/api/filesuffix/{directory}/{file}");

        response.then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExe() {
        Response response = given()
            .pathParam("directory", "bin")
            .pathParam("file", "file.exe")
        .when()
            .get("http://localhost:8080/api/filesuffix/{directory}/{file}");

        response.then().statusCode(200).body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDll() {
        Response response = given()
            .pathParam("directory", "lib")
            .pathParam("file", "file.dll")
        .when()
            .get("http://localhost:8080/api/filesuffix/{directory}/{file}");

        response.then().statusCode(200).body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoExtension() {
        Response response = given()
            .pathParam("directory", "text")
            .pathParam("file", "noext")
        .when()
            .get("http://localhost:8080/api/filesuffix/{directory}/{file}");

        response.then().statusCode(200).body(equalTo("0"));
    }
}