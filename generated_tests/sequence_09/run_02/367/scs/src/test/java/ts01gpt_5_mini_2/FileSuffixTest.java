package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtSuffix() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "document.txt");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfSuffix() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "paper.pdf");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocSuffix() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeSuffix() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "app.exe");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllSuffix() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "module.dll");
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "nofile");
        resp.then().body(equalTo("0"));
    }
}