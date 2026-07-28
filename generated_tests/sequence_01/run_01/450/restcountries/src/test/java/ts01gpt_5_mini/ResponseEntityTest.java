package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/XYZ_NON_EXISTENT");
        act.then().statusCode(404).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameInternalServerErrorProvidesStatusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        act.then().statusCode(404).body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyBadRequestReturnsBadRequestMessage() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/123");
        act.then().statusCode(404).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadRequestExposesStatusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404).body("status", equalTo(404));
    }
}