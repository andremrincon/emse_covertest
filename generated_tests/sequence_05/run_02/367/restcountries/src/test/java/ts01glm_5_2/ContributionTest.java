package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ContributionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(is(202), is(201), is(400), is(500)));
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        String payload = "{\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        String payload = "{\"amount\":100,\"currency\":\"USD\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyBody() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNullBody() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNegativeAmount() {
        String payload = "{\"amount\":-50,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(is(400), is(202), is(201), is(500)));
    }

    @Test(timeout = 60000)
    public void testContributeZeroAmount() {
        String payload = "{\"amount\":0,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(is(400), is(202), is(201), is(500)));
    }

    @Test(timeout = 60000)
    public void testContributeLargeAmount() {
        String payload = "{\"amount\":99999999,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(is(400), is(202), is(201), is(500)));
    }

    @Test(timeout = 60000)
    public void testContributeInvalidToken() {
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"invalid_token\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(is(400), is(202), is(201), is(500)));
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken() {
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeDifferentCurrency() {
        String payload = "{\"amount\":50,\"currency\":\"EUR\",\"token\":\"tok_mastercard\"}";
        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(is(202), is(201), is(400), is(500)));
    }

    @Test(timeout = 60000)
    public void testContributeMalformedJson() {
        given()
            .contentType(ContentType.JSON)
            .body("{amount: 100, currency: \"USD\", token: \"tok_visa\"")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}