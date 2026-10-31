package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        Response response = given()
            .queryParam("name", "John%20Smith")
        .when()
            .get("/");

        response.then().statusCode(lessThan(300)).body(containsString("_links"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        Response response = given()
        .when()
            .get("/");

        response.then().statusCode(lessThan(300)).body(containsString("_links"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithZeroDelay() {
        Response response = given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi");

        response.then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithPositiveDelay() {
        Response response = given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi");

        response.then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithInvalidDelay() {
        Response response = given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi");

        response.then().statusCode(401);
    }
}