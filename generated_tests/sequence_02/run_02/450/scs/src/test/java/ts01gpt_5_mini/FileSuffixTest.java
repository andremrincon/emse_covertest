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

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/text/samplefile.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/acrobat/document.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/word/report.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/bin/program.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/lib/library.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/unknown/filename");
        act.then().body(equalTo("0"));
    }
}