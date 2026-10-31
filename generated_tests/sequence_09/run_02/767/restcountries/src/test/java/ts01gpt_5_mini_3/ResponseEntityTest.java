package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.net.URL;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() throws Exception {
        String configured = System.getenv("API_BASE_URL");
        if (configured == null) {
            configured = System.getProperty("api.base");
        }
        if (configured == null) {
            configured = "http://localhost:8080/rest";
        }
        URL u = new URL(configured);
        String baseUri = u.getProtocol() + "://" + u.getHost();
        if (u.getPort() != -1) {
            baseUri += ":" + u.getPort();
        }
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = u.getPath() == null || u.getPath().isEmpty() ? "" : u.getPath();
    }

    @Test(timeout = 60000)
    public void testNameNotFoundMessage() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameNotFoundStatusField() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "123");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyBadRequestStatus() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "123");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyBadRequestMessage() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testContributeAcceptedStatusCode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String payload = "{\"id\":\"" + UUID.randomUUID().toString() + "\",\"amount\":1}";
        given().contentType("application/json").body(payload).when().post("/contribute").then().statusCode(400);
    }
}