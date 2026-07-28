package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {
    private static final String BASE;
    static {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("base.url");
        BASE = Optional.ofNullable(env).orElse(Optional.ofNullable(prop).orElse("http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testTextWithTxtReturnsOne() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange-text").then().statusCode(lessThan(300));
        String dir = "text";
        String file = "example.txt";
        Response act = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", dir, file).andReturn();
        assertEquals("1", act.asString().replace("\"", "").trim());
    }

    @Test(timeout = 60000)
    public void testAcrobatWithPdfReturnsTwo() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange-acrobat").then().statusCode(lessThan(300));
        String dir = "acrobat";
        String file = "report.pdf";
        Response act = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", dir, file).andReturn();
        assertEquals("2", act.asString().replace("\"", "").trim());
    }

    @Test(timeout = 60000)
    public void testWordWithDocReturnsThree() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange-word").then().statusCode(lessThan(300));
        String dir = "word";
        String file = "document.doc";
        Response act = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", dir, file).andReturn();
        assertEquals("3", act.asString().replace("\"", "").trim());
    }

    @Test(timeout = 60000)
    public void testBinWithExeReturnsFour() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange-bin").then().statusCode(lessThan(300));
        String dir = "bin";
        String file = "program.exe";
        Response act = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", dir, file).andReturn();
        assertEquals("4", act.asString().replace("\"", "").trim());
    }

    @Test(timeout = 60000)
    public void testLibWithDllReturnsFive() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange-lib").then().statusCode(lessThan(300));
        String dir = "lib";
        String file = "native.dll";
        Response act = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", dir, file).andReturn();
        assertEquals("5", act.asString().replace("\"", "").trim());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturnsZero() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange-nosuffix").then().statusCode(lessThan(300));
        String dir = "other";
        String file = "nofile";
        Response act = given().when().get(BASE + "/api/filesuffix/{directory}/{file}", dir, file).andReturn();
        assertEquals("0", act.asString().replace("\"", "").trim());
    }
}