package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtSuffix_shouldReturn1() {
        String file = "doc-" + UUID.randomUUID().toString() + ".txt";
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file)
                .then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdf_shouldReturn2() {
        String file = "paper-" + UUID.randomUUID().toString() + ".pdf";
        given().when().get("/api/calc/add/0/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file)
                .then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDoc_shouldReturn3() {
        String file = "report-" + UUID.randomUUID().toString() + ".doc";
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", file)
                .then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNoExtension_shouldReturn0() {
        String file = "noext-" + UUID.randomUUID().toString();
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file)
                .then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUnknownSuffix_shouldReturn0() {
        String file = "notes-" + UUID.randomUUID().toString() + ".md";
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file)
                .then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testLibDll_shouldReturn5() {
        String file = "module-" + UUID.randomUUID().toString() + ".dll";
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", file)
                .then().assertThat().body(equalTo("5"));
    }
}