package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".txt";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".pdf";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".doc";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "word", file);
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".exe";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString() + ".dll";
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String file = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/filesuffix/{directory}/{file}", "anydir", file);
        resp.then().body(equalTo("0"));
    }
}