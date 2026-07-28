package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeReturnsBadRequestWhenNoBody() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given()
                .contentType("application/json;charset=utf-8")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeReturnsBadRequestWhenBlankToken() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"\",\"id\":\"" + UUID.randomUUID().toString() + "\"}";
        given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeReturnsBadRequestOnStripeException() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":150,\"token\":\"tok_visa\",\"id\":\"" + UUID.randomUUID().toString() + "\"}";
        given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}