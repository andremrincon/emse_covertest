package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void remainder_aZero_bPositive() {
        given().when().get("/api/remainder/0/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aPositive_bPositive() {
        given().when().get("/api/remainder/17/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aPositive_bNegative() {
        given().when().get("/api/remainder/17/-5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aNegative_bPositive() {
        given().when().get("/api/remainder/-9/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aNegative_bNegative() {
        given().when().get("/api/remainder/-9/-5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_invalidInputReturns400() {
        given().when().get("/api/remainder/abc/5").then().statusCode(400);
    }
}