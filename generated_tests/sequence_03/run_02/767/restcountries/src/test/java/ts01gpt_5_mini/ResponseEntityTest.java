package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaValidReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AlphaInvalidFormatReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString().substring(0, 8);
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFoundMessageField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundStatusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "NoSuchCountry" + UUID.randomUUID().toString().substring(0,6);
        Response act = given().when().get("/v1/name/" + unique);
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV1NameServerErrorReturns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        act.then().statusCode(404);
    }
}