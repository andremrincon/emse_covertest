package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/5.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/10/1.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NLessThan2_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeXOddN_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/-1.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidNString_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/abc/2.5");
        act.then().statusCode(400);
    }
}