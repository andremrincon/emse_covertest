package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FileSuffixTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("baseUrl");
        if (url == null) url = System.getenv("BASE_URL");
        if (url == null) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testTextTxtReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "acrobat", uuid + ".pdf").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "word", uuid + ".doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "bin", uuid + ".exe").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "lib", uuid + ".dll").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "text", uuid + ".doc").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "other", "file").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "none", "archive.tar.gz").then().statusCode(lessThan(300));
        given().when().get("/api/filesuffix/{dir}/{file}", "text", uuid + ".txt").then().body(equalTo("1"));
    }
}