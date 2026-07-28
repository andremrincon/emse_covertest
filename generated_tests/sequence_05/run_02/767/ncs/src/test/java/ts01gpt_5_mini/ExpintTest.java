package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.closeTo;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String host = System.getenv().getOrDefault("BASE_HOST", System.getProperty("BASE_HOST", "http://localhost"));
        String port = System.getenv().getOrDefault("BASE_PORT", System.getProperty("BASE_PORT", "8080"));
        if (host.startsWith("http://") || host.startsWith("https://")) {
            String h = host.replaceFirst("^https?://", "");
            if (h.contains(":")) {
                String[] parts = h.split(":", 2);
                RestAssured.baseURI = "http://" + parts[0];
                try {
                    RestAssured.port = Integer.parseInt(parts[1]);
                } catch (Exception e) {
                    RestAssured.port = Integer.parseInt(port);
                }
            } else {
                RestAssured.baseURI = host;
                RestAssured.port = Integer.parseInt(port);
            }
        } else {
            RestAssured.baseURI = "http://" + host;
            RestAssured.port = Integer.parseInt(port);
        }
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionReturnsSuccessStatusField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNm1PathReturnsCalculatedResult() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/-1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNOneReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/1/0");
        resp.then().statusCode(400);
    }
}