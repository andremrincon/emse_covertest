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
        String base = System.getenv("BASE_URL");
        if (base == null) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Name_NotFound_Message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1Name_NotFound_Status() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV1Alpha_InvalidFormat_BadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_Valid_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }
}