package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
    public void testMessageWhenNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testStatusWhenNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testMessageWhenNameServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/True").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testStatusWhenNameServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/True").then().body("status", equalTo(404));
    }
}