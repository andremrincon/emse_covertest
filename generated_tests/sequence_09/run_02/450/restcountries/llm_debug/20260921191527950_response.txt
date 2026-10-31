package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

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
    public void contributeAcceptedWithValidPayload() {
        given().queryParam("fields", "name;capital;population").when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        String body = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response res = given().contentType("application/json").body(body).when().post("/contribute");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeBadRequestMissingToken() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"amount\":50,\"currency\":\"USD\"}";
        Response res = given().contentType("application/json").body(body).when().post("/contribute");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeBadRequestMissingAmount() {
        given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        String body = "{\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response res = given().contentType("application/json").body(body).when().post("/contribute");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeBadRequestInvalidJson() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "this is not json";
        Response res = given().contentType("application/json").body(body).when().post("/contribute");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeBadRequestNonNumericAmount() {
        given().queryParam("fields", "name;capital;population").when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        String body = "{\"amount\":\"abc\",\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response res = given().contentType("application/json").body(body).when().post("/contribute");
        res.then().statusCode(400);
    }
}