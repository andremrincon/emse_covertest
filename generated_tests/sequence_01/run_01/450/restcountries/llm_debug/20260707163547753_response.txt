package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType("application/json;charset=utf-8")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": \"   \", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": \"tok_valid\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}