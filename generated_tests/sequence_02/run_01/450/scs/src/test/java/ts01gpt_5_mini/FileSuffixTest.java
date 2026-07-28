package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import java.util.Optional;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = Optional.ofNullable(System.getProperty("api.base"))
                .orElse(Optional.ofNullable(System.getenv("API_BASE"))
                .orElse("http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get(base + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "text", "notes.txt");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get(base + "/api/pat/{txt}", "ping2").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "acrobat", "paper.pdf");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get(base + "/api/pat/{txt}", "ping3").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "word", "contract.doc");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get(base + "/api/pat/{txt}", "ping4").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get(base + "/api/pat/{txt}", "ping5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testFileWithNoSuffixReturns0() {
        given().when().get(base + "/api/pat/{txt}", "ping6").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "other", "README");
        resp.then().body(equalTo("0"));
    }
}