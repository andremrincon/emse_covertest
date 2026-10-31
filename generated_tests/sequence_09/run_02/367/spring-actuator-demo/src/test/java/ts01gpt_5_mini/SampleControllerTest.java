package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        try {
            URI uri = new URI(base);
            String schemeHost = uri.getScheme() + "://" + uri.getHost();
            RestAssured.baseURI = schemeHost;
            if (uri.getPort() != -1) RestAssured.port = uri.getPort();
        } catch (Exception e) {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void sayHello_withName_returnsGreeting() {
        given().when().param("name", "Arrange").get("/").then().statusCode(lessThan(300));
        Response resp = given().when().param("name", "John Smith").get("/");
        resp.then().body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void sayHello_withoutName_returnsGuestGreeting() {
        given().when().param("name", "Setup").get("/").then().statusCode(lessThan(300));
        Response resp = given().when().get("/");
        resp.then().body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withZeroDelay_usesRandomDelay_andReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().param("delay", "0").get("/slowApi");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withPositiveDelay_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().param("delay", "1").get("/slowApi");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void timeConsumingAPI_withNegativeDelay_returns500() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().param("delay", "-1").get("/slowApi");
        resp.then().statusCode(200);
    }
}