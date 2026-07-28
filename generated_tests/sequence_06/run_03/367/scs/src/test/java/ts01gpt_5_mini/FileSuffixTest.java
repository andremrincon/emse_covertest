package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import java.util.UUID;
import java.net.URL;

public class FileSuffixTest {

    @BeforeClass
    public static void init() throws Exception {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.base.url", "http://localhost:8080");
        }
        URL url = new URL(base);
        RestAssured.baseURI = url.getProtocol() + "://" + url.getHost();
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefaultPort();
        }
        RestAssured.port = port;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtSuffixReturns1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "arrange-" + uuid + ".txt").then().statusCode(lessThan(300));
        String file = "testfile-" + UUID.randomUUID().toString() + ".txt";
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfSuffixReturns2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "arrange-" + uuid + ".pdf").then().statusCode(lessThan(300));
        String file = "doc-" + UUID.randomUUID().toString() + ".pdf";
        given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocSuffixReturns3() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "word", "arrange-" + uuid + ".doc").then().statusCode(lessThan(300));
        String file = "report-" + UUID.randomUUID().toString() + ".doc";
        given().when().get("/api/filesuffix/{directory}/{file}", "word", file).then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeSuffixReturns4() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", "arrange-" + uuid + ".exe").then().statusCode(lessThan(300));
        String file = "run-" + UUID.randomUUID().toString() + ".exe";
        given().when().get("/api/filesuffix/{directory}/{file}", "bin", file).then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllSuffixReturns5() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", "arrange-" + uuid + ".dll").then().statusCode(lessThan(300));
        String file = "library-" + UUID.randomUUID().toString() + ".dll";
        given().when().get("/api/filesuffix/{directory}/{file}", "lib", file).then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "text", "arrange-" + uuid).then().statusCode(lessThan(300));
        String file = "README-" + UUID.randomUUID().toString();
        given().when().get("/api/filesuffix/{directory}/{file}", "text", file).then().body(equalTo("0"));
    }
}