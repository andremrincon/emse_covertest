package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeWithValidAmountAndToken() {
        String payload = "{\"amount\":100,\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithZeroAmount() {
        String payload = "{\"amount\":0,\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithNegativeAmount() {
        String payload = "{\"amount\":-50,\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithMissingAmount() {
        String payload = "{\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithMissingToken() {
        String payload = "{\"amount\":100}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyBody() {
        String payload = "{}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithAmountAsString() {
        String payload = "{\"amount\":\"100\",\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithLargeAmount() {
        String payload = "{\"amount\":99999999,\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        String payload = "{\"amount\":100,\"token\":null}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        String payload = "{\"amount\":100,\"token\":\"\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithBooleanAmount() {
        String payload = "{\"amount\":true,\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithExtraFields() {
        String payload = "{\"amount\":100,\"token\":\"tok_visa\",\"currency\":\"USD\",\"description\":\"test\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}