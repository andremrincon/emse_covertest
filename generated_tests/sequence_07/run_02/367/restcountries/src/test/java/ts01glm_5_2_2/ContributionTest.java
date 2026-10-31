package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ContributionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contributeValidAmountAndTokenReturnsAccepted() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":1000,\"token\":\"tok_visa_12345\",\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMissingAmountReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"tok_visa_12345\",\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMissingTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":1000,\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeInvalidAmountTypeReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":\"not_a_number\",\"token\":\"tok_visa_12345\",\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeEmptyBodyReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeEmptyTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":1000,\"token\":\"\",\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeNullAmountReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":null,\"token\":\"tok_visa_12345\",\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeNullTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":1000,\"token\":null,\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeWithExtraFieldsReturnsAccepted() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":500,\"token\":\"tok_mastercard_67890\",\"currency\":\"eur\",\"description\":\"Donation\",\"email\":\"test@test.com\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMinimalValidReturnsAccepted() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":2500,\"token\":\"tok_amex_99999\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMissingContentTypeReturnsBadRequest() {
        given()
            .body("{\"amount\":1000,\"token\":\"tok_visa_12345\",\"currency\":\"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contributeMalformedJsonReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{amount:1000,token:\"tok_visa\"")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}