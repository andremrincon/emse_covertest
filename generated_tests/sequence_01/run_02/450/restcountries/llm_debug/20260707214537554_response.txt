package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusFieldEquals404ForNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        resp.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageFieldEqualsNotFoundForNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        resp.then().body("message", equalTo("Not Found"));
    }
}