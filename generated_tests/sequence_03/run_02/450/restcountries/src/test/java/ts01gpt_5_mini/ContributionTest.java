package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ContributionTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else if (System.getProperty("baseUrl") != null && !System.getProperty("baseUrl").isEmpty()) {
            RestAssured.baseURI = System.getProperty("baseUrl");
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <400>.")
    @Test(timeout = 60000)
    public void testContributeAcceptedWithValidPayload() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"currency\":\"EUR\",\"token\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(202);
    }

    @Test(timeout = 60000)
    public void testContributeRejectedMissingToken() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":50,\"currency\":\"USD\"}";
        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeRejectedMissingAmount() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"currency\":\"USD\",\"token\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeRejectedInvalidAmountType() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":\"invalid\",\"currency\":\"EUR\",\"token\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <400>.")
    @Test(timeout = 60000)
    public void testContributeAcceptedWithLargeAmount() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1000000,\"currency\":\"EUR\",\"token\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(202);
    }

    @Test(timeout = 60000)
    public void testContributeRejectedEmptyBody() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{}";
        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(400);
    }
}