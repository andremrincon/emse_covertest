package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1NameReturnsNotFoundMessage() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        String message = act.jsonPath().getString("message");
        assertEquals("Not Found", message);
    }

    @Test(timeout = 60000)
    public void testV1NameReturnsNotFoundStatus() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        int status = act.jsonPath().getInt("status");
        assertEquals(404, status);
    }

    @Test(timeout = 60000)
    public void testV1CapitalReturnsNotFoundMessage() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        String message = act.jsonPath().getString("message");
        assertEquals("Not Found", message);
    }

    @Test(timeout = 60000)
    public void testV1RegionReturnsNotFoundStatus() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/123");
        int status = act.jsonPath().getInt("status");
        assertEquals(404, status);
    }
}