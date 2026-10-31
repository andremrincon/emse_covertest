package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl",
                System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void contributeWithBlankTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithTokenReturnsBadRequestWhenStripeFails() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"tok_visa\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithNullTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}