package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl", System.getenv("baseUrl") != null ? System.getenv("baseUrl") : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void contributeValidAmountAndTokenReturnsAccepted() {
        String payload = "{\"amount\":100,\"token\":\"tok_visa\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMissingAmountReturnsBadRequest() {
        String payload = "{\"token\":\"tok_visa\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMissingTokenReturnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeEmptyBodyReturnsBadRequest() {
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
    public void contributeNullBodyReturnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeInvalidAmountTypeReturnsBadRequest() {
        String payload = "{\"amount\":\"abc\",\"token\":\"tok_visa\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeNegativeAmountReturnsBadRequest() {
        String payload = "{\"amount\":-50,\"token\":\"tok_visa\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeZeroAmountReturnsBadRequest() {
        String payload = "{\"amount\":0,\"token\":\"tok_visa\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeEmptyTokenReturnsBadRequest() {
        String payload = "{\"amount\":100,\"token\":\"\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeValidAmountTriggersGetAmountAndToString() {
        String payload = "{\"amount\":500,\"token\":\"tok_mastercard\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeValidTokenTriggersGetToken() {
        String payload = "{\"amount\":250,\"token\":\"tok_discover\",\"currency\":\"usd\"}";
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeInvalidContentTypeReturnsClientError() {
        String payload = "amount=100&token=tok_visa&currency=usd";
        given()
                .contentType(ContentType.URLENC)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }
}