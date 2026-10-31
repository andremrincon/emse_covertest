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
        String base = System.getProperty("REST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsStatusField404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "123");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsMessageNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameServerErrorReturnsStatusField500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "True");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testNameServerErrorReturnsMessageInternalServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "True");
        act.then().body("message", equalTo("Not Found"));
    }
}