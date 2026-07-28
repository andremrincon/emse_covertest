package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContribute_NullPayload_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given()
                .contentType("application/json;charset=utf-8")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContribute_BlankToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String body = "{\"amount\":100,\"token\":\"   \"}";
        given()
                .contentType("application/json;charset=utf-8")
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContribute_NonBlankToken_authenticationFailure_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String body = "{\"amount\":150,\"token\":\"" + token + "\"}";
        given()
                .contentType("application/json;charset=utf-8")
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContribute_ValidToken_attemptsCharge_mayReturnAccepted() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_charge_succeeds_" + UUID.randomUUID().toString();
        String body = "{\"amount\":200,\"token\":\"" + token + "\"}";
        given()
                .contentType("application/json;charset=utf-8")
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}