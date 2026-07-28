package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;
import java.util.UUID;
import java.net.URLEncoder;

public class FileSuffixTest {
    private static final String BASE = initBase();
    private static String initBase() {
        String v = System.getProperty("BASE_URL");
        if (v == null || v.isEmpty()) v = System.getenv("BASE_URL");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        return v;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturns1() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("arrange-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        String dir = "text";
        String file = "document.txt";
        Response resp = given().when().get(BASE + "/api/filesuffix/" + URLEncoder.encode(dir, "UTF-8") + "/" + URLEncoder.encode(file, "UTF-8"));
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAcrobatPdfReturns2() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("arrange-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        String dir = "acrobat";
        String file = "paper.pdf";
        Response resp = given().when().get(BASE + "/api/filesuffix/" + URLEncoder.encode(dir, "UTF-8") + "/" + URLEncoder.encode(file, "UTF-8"));
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordDocReturns3() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("arrange-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        String dir = "word";
        String file = "report.doc";
        Response resp = given().when().get(BASE + "/api/filesuffix/" + URLEncoder.encode(dir, "UTF-8") + "/" + URLEncoder.encode(file, "UTF-8"));
        Assert.assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBinExeReturns4() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("arrange-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        String dir = "bin";
        String file = "run.exe";
        Response resp = given().when().get(BASE + "/api/filesuffix/" + URLEncoder.encode(dir, "UTF-8") + "/" + URLEncoder.encode(file, "UTF-8"));
        Assert.assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testLibDllReturns5() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("arrange-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        String dir = "lib";
        String file = "native.dll";
        Response resp = given().when().get(BASE + "/api/filesuffix/" + URLEncoder.encode(dir, "UTF-8") + "/" + URLEncoder.encode(file, "UTF-8"));
        Assert.assertEquals("5", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("arrange-" + uuid, "UTF-8")).then().statusCode(lessThan(300));
        String dir = "misc";
        String file = "README";
        Response resp = given().when().get(BASE + "/api/filesuffix/" + URLEncoder.encode(dir, "UTF-8") + "/" + URLEncoder.encode(file, "UTF-8"));
        Assert.assertEquals("0", resp.getBody().asString());
    }
}