package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextSuffixReturnsOne() {
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "doc.doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "app.exe").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "lib.dll").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "other", "nofile").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "readme.txt").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "readme.txt").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "doc.doc").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "unknown", "file");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns200() {
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "readme.txt").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "doc.doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "app.exe").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "lib.dll").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "manual.pdf").then().statusCode(200);
    }
}