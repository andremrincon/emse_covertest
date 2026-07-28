package ts01gpt_5_mini;

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
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        if (base.contains("/rest")) {
            int idx = base.indexOf("/rest");
            RestAssured.baseURI = base.substring(0, idx);
            RestAssured.basePath = base.substring(idx);
        } else {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBody_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().contentType(ContentType.JSON).body("null").when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String json = "{\"amount\":1000,\"token\":\"\"}";
        Response resp = given().contentType(ContentType.JSON).body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String json = "{\"amount\":500,\"token\":\"   \"}";
        Response resp = given().contentType(ContentType.JSON).body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithoutTokenField_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String json = "{\"amount\":250}";
        Response resp = given().contentType(ContentType.JSON).body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNonBlankToken_returnsBadRequestDueToStripeFailure() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String json = "{\"amount\":1234,\"token\":\"" + token + "\"}";
        Response resp = given().contentType(ContentType.JSON).body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }
}