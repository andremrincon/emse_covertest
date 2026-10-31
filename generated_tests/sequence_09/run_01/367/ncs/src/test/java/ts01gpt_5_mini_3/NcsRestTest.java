package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_Returns200_WhenNValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_Returns400_WhenNTooSmall() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns200_WhenValid() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_WhenMTooLarge() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1001/5/0.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_ReturnsExpectedResultBody() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        resp.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainder_Returns400_WhenOutOfBounds() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17000/5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_Returns200_WhenValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Returns200_WhenValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/2.3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangle_Returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}