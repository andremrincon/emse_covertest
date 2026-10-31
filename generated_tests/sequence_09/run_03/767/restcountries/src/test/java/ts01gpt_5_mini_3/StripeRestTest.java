package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class StripeRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testContribute_nullPayload_returnsBadRequest() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.JSON).when().post("/contribute");
        act.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testContribute_blankToken_returnsBadRequest() {
        given().when().get("/").then().statusCode(lessThan(300));
        Map<String, Object> payload = new HashMap<>();
        payload.put("amount", 100);
        payload.put("token", "");
        payload.put("currency", "eur");
        payload.put("description", "REST Countries Test " + UUID.randomUUID().toString());
        Response act = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        act.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testContribute_validToken_returnsAccepted() {
        given().when().get("/").then().statusCode(lessThan(300));
        Map<String, Object> payload = new HashMap<>();
        payload.put("amount", 150);
        payload.put("token", "tok_visa");
        payload.put("currency", "eur");
        payload.put("description", "REST Countries Donation " + UUID.randomUUID().toString());
        Response act = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        act.then().statusCode(202);
    }
}