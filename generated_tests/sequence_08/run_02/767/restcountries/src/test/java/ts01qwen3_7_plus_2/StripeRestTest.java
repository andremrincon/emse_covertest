package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeWithMissingToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithInvalidTokenCausesException() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}