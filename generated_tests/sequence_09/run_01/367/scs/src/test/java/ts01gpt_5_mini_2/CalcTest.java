package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.net.URI;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() throws Exception {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        URI uri = new URI(base);
        String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
        String host = uri.getHost();
        if (host == null) {
            String s = uri.toString();
            if (s.startsWith("http://")) s = s.substring(7);
            else if (s.startsWith("https://")) s = s.substring(8);
            if (s.contains("/")) s = s.substring(0, s.indexOf("/"));
            if (s.contains(":")) {
                String[] parts = s.split(":");
                host = parts[0];
                RestAssured.baseURI = scheme + "://" + host;
                RestAssured.port = Integer.parseInt(parts[1]);
                return;
            } else {
                host = s;
            }
        }
        RestAssured.baseURI = scheme + "://" + host;
        if (uri.getPort() != -1) RestAssured.port = uri.getPort();
    }

    @Test(timeout = 60000)
    public void testPiReturnsPiString() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String body = given().when().get("/api/calc/pi/0/0").then().extract().asString();
        Assert.assertEquals(String.valueOf(Math.PI), body);
    }

    @Test(timeout = 60000)
    public void testPlusOperationReturns200() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/cookie/" + uniq + "/1/localhost").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubtractReturnsCorrectBody() {
        given().when().get("/api/ordered4/zebra/yak/x-ray/wolf").then().statusCode(lessThan(300));
        String body = given().when().get("/api/calc/subtract/10/2").then().extract().asString();
        Assert.assertEquals("8.0", body);
    }
}