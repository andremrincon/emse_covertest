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
        String base = System.getProperty("rest.base");
        if (base == null) base = System.getenv("REST_BASE");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetMessageFromV1NameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        assertEquals("Not Found", resp.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testGetStatusFromV1CapitalNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/123");
        assertEquals(404, (int) resp.jsonPath().getInt("status"));
    }
}