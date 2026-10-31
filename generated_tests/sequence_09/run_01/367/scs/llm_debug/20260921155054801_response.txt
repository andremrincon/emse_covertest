package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            base = env;
        } else {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFileReturns200() {
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "text", "document.txt");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfFileReturns200() {
        given().when().get(base + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "acrobat", "presentation.pdf");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocFileReturns200() {
        given().when().get(base + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "word", "report.doc");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeFileReturns200() {
        given().when().get(base + "/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllFileReturns200() {
        given().when().get(base + "/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        resp.then().statusCode(200);
    }
}