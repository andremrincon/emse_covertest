package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFileReturns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "example.txt");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfFileReturns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "doc.pdf");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocFileReturns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeFileReturns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllFileReturns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoSuffixFileReturns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "README");
        resp.then().statusCode(200);
    }
}