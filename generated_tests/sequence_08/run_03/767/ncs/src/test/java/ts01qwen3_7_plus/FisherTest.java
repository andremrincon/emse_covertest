package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddN() {
        given().when().get("/api/fisher/1/1/0.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/1/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddN() {
        given().when().get("/api/fisher/2/1/0.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/2/1/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given().when().get("/api/fisher/1/2/0.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/2/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given().when().get("/api/fisher/2/2/0.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/2/2/0.5").then().statusCode(200);
    }
}