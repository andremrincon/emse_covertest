package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getenv("BASE_URL"))
                .orElse(System.getProperty("baseUrl", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 2, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 4, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", "abc", 5, 0.75);
        resp.then().statusCode(400);
    }
}