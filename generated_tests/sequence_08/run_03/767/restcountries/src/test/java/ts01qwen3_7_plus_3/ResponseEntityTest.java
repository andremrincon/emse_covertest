package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetStatusFromNameEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/123");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageFromCapitalEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/123");
        response.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusFromBodyFromRegionEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/123");
        response.then().body("status", equalTo(404));
    }
}