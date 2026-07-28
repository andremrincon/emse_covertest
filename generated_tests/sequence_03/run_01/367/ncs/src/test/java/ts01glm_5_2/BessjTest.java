package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        RestAssured.given().when().get("/api/bessj/1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        RestAssured.given().when().get("/api/bessj/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXGreaterThanN() {
        RestAssured.given().when().get("/api/bessj/3/5.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXLessThanOrEqualToN() {
        RestAssured.given().when().get("/api/bessj/5/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXNegativeLarge() {
        RestAssured.given().when().get("/api/bessj/3/-10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXPositiveLarge() {
        RestAssured.given().when().get("/api/bessj/3/10.0").then().statusCode(200);
    }
}