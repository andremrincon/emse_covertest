package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        BASE = b;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        given().when().get(BASE + "/api/calc/add/1/1").then().statusCode(lessThan(300));
        String fname = UUID.randomUUID().toString() + ".txt";
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "text", fname);
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        given().when().get(BASE + "/api/calc/add/2/3").then().statusCode(lessThan(300));
        String fname = UUID.randomUUID().toString() + ".pdf";
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "acrobat", fname);
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        given().when().get(BASE + "/api/calc/add/3/4").then().statusCode(lessThan(300));
        String fname = UUID.randomUUID().toString() + ".doc";
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "word", fname);
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        given().when().get(BASE + "/api/calc/add/4/5").then().statusCode(lessThan(300));
        String fname = UUID.randomUUID().toString() + ".exe";
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "bin", fname);
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        given().when().get(BASE + "/api/calc/add/5/6").then().statusCode(lessThan(300));
        String fname = UUID.randomUUID().toString() + ".dll";
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "lib", fname);
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testFileWithoutSuffixReturns0() {
        given().when().get(BASE + "/api/calc/add/6/7").then().statusCode(lessThan(300));
        String fname = UUID.randomUUID().toString();
        Response resp = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", "text", fname);
        resp.then().body(equalTo("0"));
    }
}