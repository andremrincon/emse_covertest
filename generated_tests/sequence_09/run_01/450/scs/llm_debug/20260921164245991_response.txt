package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode("setup-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/file.txt");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode("setup-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/acrobat/document.pdf");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode("setup-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/word/report.doc");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode("setup-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/bin/program.exe");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode("setup-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/lib/native.dll");
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode("setup-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/README");
        resp.then().body(equalTo("0"));
    }
}