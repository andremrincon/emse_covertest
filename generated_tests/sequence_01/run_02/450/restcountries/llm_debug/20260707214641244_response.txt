package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyPayload() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidJson() {
        given()
            .contentType(ContentType.JSON)
            .body("invalid json")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNullAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": null, \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": null}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNegativeAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": -10, \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeZeroAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 0, \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeStringAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": \"abc\", \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}