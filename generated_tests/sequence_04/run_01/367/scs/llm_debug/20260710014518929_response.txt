package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String env = Optional.ofNullable(System.getenv("API_BASE")).orElse(System.getProperty("api.base", "http://localhost:8080"));
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testTextSuffixReturns1() {
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "document.doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "readme.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatSuffixReturns2() {
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "notes.md").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "misc", "noext").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordSuffixReturns3() {
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "a.b.c.txt").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "x.y.pdf").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "contract.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinSuffixReturns4() {
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "notes.doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "mod.dll").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibSuffixReturns5() {
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.bat").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "file.pdf").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "justname").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "archive.tar.gz").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "unknown", "nofile");
        act.then().body(equalTo("0"));
    }
}