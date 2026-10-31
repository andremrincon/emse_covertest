package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    private static final String BASE = System.getenv("API_BASE_URL") != null ? System.getenv("API_BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() throws Exception {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        String dir = "text";
        String file = "example.txt";
        String url = BASE + "/api/filesuffix/" + URLEncoder.encode(dir, StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode(file, StandardCharsets.UTF_8.name());
        Response resp = given().when().get(url);
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() throws Exception {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        String dir = "acrobat";
        String file = "document.pdf";
        String url = BASE + "/api/filesuffix/" + URLEncoder.encode(dir, StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode(file, StandardCharsets.UTF_8.name());
        Response resp = given().when().get(url);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() throws Exception {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        String dir = "word";
        String file = "letter.doc";
        String url = BASE + "/api/filesuffix/" + URLEncoder.encode(dir, StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode(file, StandardCharsets.UTF_8.name());
        Response resp = given().when().get(url);
        assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() throws Exception {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        String dir = "bin";
        String file = "runme.exe";
        String url = BASE + "/api/filesuffix/" + URLEncoder.encode(dir, StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode(file, StandardCharsets.UTF_8.name());
        Response resp = given().when().get(url);
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() throws Exception {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        String dir = "lib";
        String file = "library.dll";
        String url = BASE + "/api/filesuffix/" + URLEncoder.encode(dir, StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode(file, StandardCharsets.UTF_8.name());
        Response resp = given().when().get(url);
        assertEquals("5", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoExtensionReturns0() throws Exception {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        String dir = "text";
        String file = "noextension";
        String url = BASE + "/api/filesuffix/" + URLEncoder.encode(dir, StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode(file, StandardCharsets.UTF_8.name());
        Response resp = given().when().get(url);
        assertEquals("0", resp.asString());
    }
}