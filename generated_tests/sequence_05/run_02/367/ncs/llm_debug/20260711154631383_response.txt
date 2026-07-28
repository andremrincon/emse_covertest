package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherEvenEven() {
        given().when().get("/api/fisher/2/2/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/2/2/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddOdd() {
        given().when().get("/api/fisher/1/1/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/1/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEven() {
        given().when().get("/api/fisher/1/2/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/2/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOdd() {
        given().when().get("/api/fisher/2/1/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/2/1/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNegativeP() {
        given().when().get("/api/fisher/6/2/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/6/2/1e-20").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherGreaterThanOneP() {
        given().when().get("/api/fisher/1/1/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/1/1e100").then().statusCode(200);
    }
}