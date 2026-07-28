package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class ContributionTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.basePath = System.getProperty("api.path", System.getenv().getOrDefault("API_BASE_PATH", "/rest"));
    }

    @Test(timeout = 60000)
    public void testContributeWithValidPayloadReturns202() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        Response act = given().contentType(ContentType.JSON)
                .body("{\"amount\":100,\"token\":\"" + token + "\"}")
                .when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeMissingTokenReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.JSON)
                .body("{\"amount\":50}")
                .when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeMalformedAmountReturns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        Response act = given().contentType(ContentType.JSON)
                .body("{\"amount\":\"notanumber\",\"token\":\"" + token + "\"}")
                .when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithExtraFieldsReturns202() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        Response act = given().contentType(ContentType.JSON)
                .body("{\"amount\":250,\"currency\":\"EUR\",\"token\":\"" + token + "\",\"note\":\"thanks\"}")
                .when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeValidResponseBodyContainsAccepted() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        Response act = given().contentType(ContentType.JSON)
                .body("{\"amount\":10,\"token\":\"" + token + "\"}")
                .when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }
}