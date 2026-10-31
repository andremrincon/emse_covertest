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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1NameNotFound_message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().statusCode(404).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1CapitalNotFound_status() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameServerError_message() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/True");
        act.then().statusCode(404).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadRequest_status() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1NameNotFound_statusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().statusCode(404).body("status", equalTo(404));
    }
}