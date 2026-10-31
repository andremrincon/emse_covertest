package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private String base() {
        return Optional.ofNullable(System.getenv("BASE_URL"))
                .orElse(Optional.ofNullable(System.getProperty("baseUrl"))
                        .orElse("http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionLargeX() {
        given().baseUri(base()).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(base()).when().get("/api/expint/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesSmallX() {
        given().baseUri(base()).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(base()).when().get("/api/expint/{n}/{x}", 3, 0.1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroPath() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(base()).when().get("/api/expint/{n}/{x}", 0, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroN2ReturnsFinite() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(base()).when().get("/api/expint/{n}/{x}", 2, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidNegativeNProducesBadRequest() {
        given().baseUri(base()).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(base()).when().get("/api/expint/{n}/{x}", -1, 1.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroN1ProducesBadRequest() {
        given().baseUri(base()).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(base()).when().get("/api/expint/{n}/{x}", 1, 0.0);
        act.then().statusCode(400);
    }
}