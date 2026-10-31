package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns200() {
        String uniqueFile = UUID.randomUUID().toString() + ".txt";
        given().when().get("/api/pat/{txt}", "arrange-text").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", uniqueFile).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns200() {
        String uniqueFile = UUID.randomUUID().toString() + ".pdf";
        given().when().get("/api/pat/{txt}", "arrange-acrobat").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", uniqueFile).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDocReturns200() {
        String uniqueFile = UUID.randomUUID().toString() + ".doc";
        given().when().get("/api/pat/{txt}", "arrange-word").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", uniqueFile).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExeReturns200() {
        String uniqueFile = UUID.randomUUID().toString() + ".exe";
        given().when().get("/api/pat/{txt}", "arrange-bin").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", uniqueFile).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDllReturns200() {
        String uniqueFile = UUID.randomUUID().toString() + ".dll";
        given().when().get("/api/pat/{txt}", "arrange-lib").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", uniqueFile).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFileWithoutSuffixReturns200() {
        String uniqueFile = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-nosuffix").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", uniqueFile).then().statusCode(200);
    }
}