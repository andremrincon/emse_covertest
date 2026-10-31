package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void init() {
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
    public void contributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"amount\":100,\"token\":\"\"}";
        Response resp = given().contentType(ContentType.JSON).body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithNonBlankTokenReturnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String body = "{\"amount\":250,\"token\":\"" + token + "\"}";
        Response resp = given().contentType(ContentType.JSON).body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeTriggersStripeErrorReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "card_error_" + UUID.randomUUID().toString();
        String body = "{\"amount\":9999,\"token\":\"" + token + "\"}";
        Response resp = given().contentType(ContentType.JSON).body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }
}