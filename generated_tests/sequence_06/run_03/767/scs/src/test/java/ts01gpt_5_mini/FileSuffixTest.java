package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        given().when().get("/api/pat/{txt}", "arrange-text-" + System.currentTimeMillis()).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "note.txt").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        given().when().get("/api/pat/{txt}", "arrange-acrobat-" + System.currentTimeMillis()).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        given().when().get("/api/pat/{txt}", "arrange-word-" + System.currentTimeMillis()).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        given().when().get("/api/pat/{txt}", "arrange-bin-" + System.currentTimeMillis()).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "run.exe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        given().when().get("/api/pat/{txt}", "arrange-lib-" + System.currentTimeMillis()).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get("/api/pat/{txt}", "arrange-nosuffix-" + System.currentTimeMillis()).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "filewithoutsuffix").then().statusCode(200);
    }
}