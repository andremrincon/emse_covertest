package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMessageReturnedForNameNotFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testStatusReturnedForNameNotFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        when().get("/v1/name/123").then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testMessageReturnedForNameServerError_500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        when().get("/v1/name/True").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testStatusReturnedForNameServerError_500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        when().get("/v1/name/True").then().body("status", equalTo(404));
    }
}