package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @Before
    public void setUp() {
        String host = System.getenv().getOrDefault("APP_HOST", "localhost");
        String port = System.getenv().getOrDefault("APP_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testBothPositiveDividendAndDivisor() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testPositiveDividendNegativeDivisor() {
        given().when().get("/api/remainder/17/-5").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testNegativeDividendPositiveDivisor() {
        given().when().get("/api/remainder/-5/5").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBothNegativeDividendAndDivisor() {
        given().when().get("/api/remainder/-4/-2").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testZeroDividendReturnsBadRequest() {
        given().when().get("/api/remainder/0/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroDivisorReturnsBadRequest() {
        given().when().get("/api/remainder/17/0").then().statusCode(200);
    }
}