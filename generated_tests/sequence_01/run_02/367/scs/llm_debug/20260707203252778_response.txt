package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void textDirectoryWithTxtExtensionReturns1() {
        String uu = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "hc-"+uu).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "text", "example."+ "txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void acrobatDirectoryWithPdfExtensionReturns2() {
        String uu = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "hc-"+uu).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void wordDirectoryWithDocExtensionReturns3() {
        String uu = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "hc-"+uu).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "word", "letter.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void binDirectoryWithExeExtensionReturns4() {
        String uu = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "hc-"+uu).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "bin", "installer.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void libDirectoryWithDllExtensionReturns5() {
        String uu = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "hc-"+uu).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "lib", "native.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void noExtensionReturns0() {
        String uu = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", "hc-"+uu).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/filesuffix/{directory}/{file}", "text", "filename");
        act.then().body(equalTo("0"));
    }
}