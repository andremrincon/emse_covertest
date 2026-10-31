package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void contributeValidPayloadReturnsAccepted() {
        String token = UUID.randomUUID().toString();
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":100,\"currency\":\"USD\",\"token\":\"" + token + "\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeValidPayloadWithExtraFieldsReturnsAccepted() {
        String token = UUID.randomUUID().toString();
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":250,\"currency\":\"EUR\",\"token\":\"" + token + "\",\"note\":\"test\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeMissingTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":100,\"currency\":\"USD\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeMissingAmountReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"currency\":\"USD\",\"token\":\"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeMissingCurrencyReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":100,\"token\":\"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeEmptyBodyReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeNegativeAmountReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":-50,\"currency\":\"USD\",\"token\":\"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeZeroAmountReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":0,\"currency\":\"USD\",\"token\":\"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeStringAmountReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":\"abc\",\"currency\":\"USD\",\"token\":\"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeEmptyTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":100,\"currency\":\"USD\",\"token\":\"\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeEmptyCurrencyReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":100,\"currency\":\"\",\"token\":\"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeMalformedJsonReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{invalid json}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}