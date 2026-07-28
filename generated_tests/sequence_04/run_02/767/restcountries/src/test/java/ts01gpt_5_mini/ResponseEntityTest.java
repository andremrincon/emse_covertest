package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNameEndpointReturnsStatusFieldForNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "NonExistentCountryXYZ").then().statusCode(404).extract().response();
        assertEquals(404, (int) act.jsonPath().getInt("status"));
    }

    @Test(timeout = 60000)
    public void testNameEndpointReturnsMessageFieldForServerErrorExample() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "True").then().statusCode(404).extract().response();
        assertEquals("Not Found", act.jsonPath().getString("message"));
    }
}