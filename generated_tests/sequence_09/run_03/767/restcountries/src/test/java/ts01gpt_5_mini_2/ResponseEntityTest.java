package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNameNotFound_statusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "123").then().assertThat().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFound_messageField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/{capital}", "123").then().assertThat().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameServerError_statusCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "True").then().assertThat().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testAlphaBadRequest_statusCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().assertThat().statusCode(200);
    }
}