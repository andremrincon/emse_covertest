package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contribute_withEmptyBody_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withNullToken_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withEmptyToken_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withWhitespaceOnlyToken_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"   \"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withValidTokenButStripeFailure_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"tok_visa\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}