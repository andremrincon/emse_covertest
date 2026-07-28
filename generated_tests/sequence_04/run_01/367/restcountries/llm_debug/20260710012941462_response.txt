package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contribute_withNullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withEmptyToken_returnsBadRequest() {
        String body = "{\"token\":\"\",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withWhitespaceOnlyToken_returnsBadRequest() {
        String body = "{\"token\":\"   \",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withNullToken_returnsBadRequest() {
        String body = "{\"token\":null,\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withValidTokenButStripeFailure_returnsBadRequest() {
        String body = "{\"token\":\"tok_visa\",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }
}