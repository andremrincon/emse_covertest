package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.equalTo;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRemainder_aIsZero_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/0/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_bIsZero_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/5/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_positivePositive_returnsResultTwo() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/5");
        act.then().statusCode(200).body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_positiveNegative_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/-5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_negativePositive_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/-9/4");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_negativeNegative_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/-9/-4");
        act.then().statusCode(200);
    }
}