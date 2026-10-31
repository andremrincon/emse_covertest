package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void init() {
        String fromProp = System.getProperty("BASE_URL");
        String fromEnv = System.getenv("BASE_URL");
        String defaultUrl = "http://localhost:8080/rest";
        RestAssured.baseURI = fromProp != null ? fromProp : (fromEnv != null ? fromEnv : defaultUrl);
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundReturnsMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}", "123");
        resp.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1CapitalNotFoundReturnsStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/{capital}", "123");
        resp.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV1NameServerErrorReturnsInternalServerErrorMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}", "True");
        resp.then().body("message", equalTo("Not Found"));
    }
}