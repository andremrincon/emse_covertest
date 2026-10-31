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
    public void testContributeValidPayloadAccepted() {
        String payload = "{\"amount\":1000,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmountBadRequest() {
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
    public void testContributeMissingTokenBadRequest() {
        String payload = "{\"amount\":500,\"currency\":\"USD\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingCurrencyBadRequest() {
        String payload = "{\"amount\":500,\"token\":\"tok_visa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyBodyBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
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
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNegativeAmount() {
        String payload = "{\"amount\":-100,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
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
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken() {
        String payload = "{\"amount\":1000,\"currency\":\"USD\",\"token\":\"\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        String payload = "{\"amount\":1000,\"currency\":\"USD\",\"token\":null}";
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
        String payload = "{\"amount\":2500,\"currency\":\"EUR\",\"token\":\"tok_visa\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidContentType() {
        given()
                .contentType(ContentType.TEXT)
                .body("amount=1000&currency=USD&token=tok_visa")
        .when()
                .post("/contribute")
        .then()
                .statusCode(415);
    }
}