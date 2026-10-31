package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("null")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}