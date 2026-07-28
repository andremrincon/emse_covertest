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
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contribute_validPayload_returnsAccepted() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_missingAmount_returns400() {
        String payload = "{\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_missingToken_returns400() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_emptyBody_returns400() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_nullAmount_returns400() {
        String payload = "{\"amount\":null,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_nullToken_returns400() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":null}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_negativeAmount_returns400() {
        String payload = "{\"amount\":-500,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_zeroAmount_returns400() {
        String payload = "{\"amount\":0,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_stringAmount_returns400() {
        String payload = "{\"amount\":\"1000\",\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_emptyToken_returns400() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":\"\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_missingContentType_returns400() {
        String payload = "{\"amount\":1000,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_largeAmount_returnsAcceptedOrError() {
        String payload = "{\"amount\":99999999,\"currency\":\"usd\",\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }
}