package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherConstructor() throws Exception {
        given().when().get("/api/fisher/1/1/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1() {
        given().when().get("/api/fisher/1/1/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b2() {
        given().when().get("/api/fisher/3/2/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a2_b1() {
        given().when().get("/api/fisher/2/3/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a2_b2() {
        given().when().get("/api/fisher/4/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pLessThanZero() {
        given().when().get("/api/fisher/4/4/100.0").then().statusCode(200);
    }
}