package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
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
    public void testTextTxtSuffix_Returns1() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "file.txt").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfSuffix_Returns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "document.pdf").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocSuffix_Returns3() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "report.doc").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeSuffix_Returns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "program.exe").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllSuffix_Returns5() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll").then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{directory}/{file}", "other", "file").then().body(equalTo("0"));
    }
}