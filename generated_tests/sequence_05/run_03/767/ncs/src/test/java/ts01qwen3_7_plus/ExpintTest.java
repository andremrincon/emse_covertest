package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import org.junit.Test;

public class ExpintTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testExpintErrorConditionNegativeN() {
        Response response = given()
        .when()
            .get(BASE_URL + "/api/expint/-1/2.5");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        Response response = given()
        .when()
            .get(BASE_URL + "/api/expint/3/2.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualToOne() {
        Response response = given()
        .when()
            .get(BASE_URL + "/api/expint/3/0.1");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionFailure() {
        Response response = given()
        .when()
            .get(BASE_URL + "/api/expint/10000/10000.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesFailure() {
        Response response = given()
        .when()
            .get(BASE_URL + "/api/expint/10000/0.999999999");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintErrorConditionZeroXZeroN() {
        Response response = given()
        .when()
            .get(BASE_URL + "/api/expint/0/0.0");
        response.then().statusCode(400);
    }
}