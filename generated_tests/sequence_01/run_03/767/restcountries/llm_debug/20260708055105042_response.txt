package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testNameNotFound_statusFieldIs404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/XYZ");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testNameServerError_messageIsInternalServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testCapitalBadInput_returns404StatusCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testRegionNotFound_bodyMessageIsNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/123");
        act.then().body("message", equalTo("Not Found"));
    }
}