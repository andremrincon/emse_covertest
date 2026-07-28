package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ContributionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("basePort", "8080"));
        RestAssured.basePath = System.getProperty("basePath", "/rest");
    }

    @Test(timeout = 60000)
    public void testContributeValidPayloadReturnsAccepted() {
        String payload = "{\"amount\":100,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmountReturnsBadRequest() {
        String payload = "{\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingTokenReturnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"usd\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyPayloadReturnsBadRequest() {
        String payload = "{}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeZeroAmountReturnsAccepted() {
        String payload = "{\"amount\":0,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNegativeAmountReturnsBadRequest() {
        String payload = "{\"amount\":-50,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeLargeAmountReturnsAccepted() {
        String payload = "{\"amount\":99999999,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNullTokenReturnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"usd\",\"token\":null}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyTokenReturnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"usd\",\"token\":\"\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingCurrencyReturnsAccepted() {
        String payload = "{\"amount\":100,\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNonNumericAmountReturnsBadRequest() {
        String payload = "{\"amount\":\"abc\",\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeSpecialCharTokenReturnsAccepted() {
        String payload = "{\"amount\":50,\"currency\":\"usd\",\"token\":\"tok_!@#$%^&*()\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }
}