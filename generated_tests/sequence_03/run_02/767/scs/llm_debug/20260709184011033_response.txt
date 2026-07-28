package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFilesuffix_noSuffix_returnsZero() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "anydir", "file");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFilesuffix_textTxt_returnsOne() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", "note.txt");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFilesuffix_acrobatPdf_returnsTwo() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "doc.pdf");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFilesuffix_wordDoc_returnsThree() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testFilesuffix_binExe_returnsFour() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testFilesuffix_libDll_returnsFive() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        resp.then().body(equalTo("5"));
    }
}