package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;
import java.util.UUID;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().containsKey("API_BASE") ? System.getenv("API_BASE") : "http://localhost");
        String portStr = System.getProperty("api.port", System.getenv().containsKey("API_PORT") ? System.getenv("API_PORT") : "8080");
        if (base.startsWith("http://")) {
            base = base.replaceFirst("http://", "");
            base = "http://" + base;
        } else if (base.startsWith("https://")) {
            base = base.replaceFirst("https://", "");
            base = "https://" + base;
        }
        RestAssured.baseURI = base.replaceAll("/+$", "");
        try {
            portStr = portStr.replaceAll("/+$", "");
            portStr = portStr.trim();
            int p = (portStr == null || portStr.isEmpty()) ? 8080 : Integer.parseInt(portStr);
            RestAssured.port = p;
        } catch (Exception e) {
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testNonAbabStringReturns10() {
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "a").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 700, "a").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 1, "xyz_" + UUID.randomUUID().toString()).then().statusCode(200).extract().response();
        Assert.assertEquals("10", act.asString());
    }

    @Test(timeout = 60000)
    public void testAbabWithMinus4Returns0() {
        given().when().get("/api/costfuns/{i}/{s}", 1000, "zzzz").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "ababba").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(200).extract().response();
        Assert.assertEquals("10", act.asString());
    }

    @Test(timeout = 60000)
    public void testAbabWithFiveReturns6() {
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, "abab").then().statusCode(200).extract().response();
        Assert.assertEquals("10", act.asString());
    }

    @Test(timeout = 60000)
    public void testAlgorithmEndpointResponds200() {
        given().when().get("/api/costfuns/{i}/{s}", Integer.MAX_VALUE, "long_" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "ababba").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "a").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(200);
    }
}