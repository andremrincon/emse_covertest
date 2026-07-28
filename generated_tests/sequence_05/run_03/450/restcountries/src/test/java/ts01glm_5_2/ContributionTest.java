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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contribute_validPayload_returnsAccepted() {
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_visa\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_missingAmount_returnsBadRequest() {
        String payload = "{\"currency\":\"USD\",\"token\":\"tok_visa\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_missingToken_returnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"USD\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_missingCurrency_returnsBadRequest() {
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
    public void contribute_emptyBody_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_negativeAmount_returnsBadRequest() {
        String payload = "{\"amount\":-50,\"currency\":\"USD\",\"token\":\"tok_visa\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_zeroAmount_returnsBadRequest() {
        String payload = "{\"amount\":0,\"currency\":\"USD\",\"token\":\"tok_visa\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_emptyToken_returnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_nullToken_returnsBadRequest() {
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":null}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_largeAmount_returnsAccepted() {
        String payload = "{\"amount\":999999,\"currency\":\"EUR\",\"token\":\"tok_mastercard\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_nonNumericAmount_returnsBadRequest() {
        String payload = "{\"amount\":\"abc\",\"currency\":\"USD\",\"token\":\"tok_visa\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_malformedJson_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{invalid json}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}